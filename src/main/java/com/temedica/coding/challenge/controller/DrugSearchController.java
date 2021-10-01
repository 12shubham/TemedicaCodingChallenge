package com.temedica.coding.challenge.controller;

import com.temedica.coding.challenge.entity.Drug;
import com.temedica.coding.challenge.entity.Drugs;
import com.temedica.coding.challenge.service.DrugSearchService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class DrugSearchController {

    private final DrugSearchService drugSearchService;

    @GetMapping(value = "/search")
    @ResponseBody
    public List<Drug> getRelevantDrugs(@RequestParam("searchstring") String searchstring) throws ParseException {
        if(searchstring.isEmpty()) return new ArrayList<>();
        List<Drug> relevantDrugs = drugSearchService.getRelevantDrugs(searchstring);
        Set<String> namesAlreadySeen = new HashSet<>();
        relevantDrugs.removeIf(p -> !namesAlreadySeen.add(p.getId()));
        Map<String, String> allDescriptions = new HashMap<>();
        Map<String, String> allDiseases = new HashMap<>();
        for(Drug eachDrug : relevantDrugs){
            //Descriptiuon processing
            char[] descriptionChars = eachDrug.getDescription();
            eachDrug.setVarDisplayDescription(new String(descriptionChars));

            //Diseases processing
            eachDrug.setVarDiseases(String.join(", ", eachDrug.getDiseases()));

            //Release date processing
            LocalDate date = eachDrug.getReleased();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            eachDrug.setVarDateString(formatter.format(date));
        }
        return relevantDrugs;
    }

    @GetMapping({"/","","/index.html","/index"})
    String getSearchView(Model model){
        List<Drug> relevantDrugs = new ArrayList<>();
        model.addAttribute("allRelevantDrugs", relevantDrugs);
        model.addAttribute("condition", false);
        return "index";
    }

}
