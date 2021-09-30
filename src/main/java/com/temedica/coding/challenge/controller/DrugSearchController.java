package com.temedica.coding.challenge.controller;

import com.temedica.coding.challenge.entity.Drug;
import com.temedica.coding.challenge.service.DrugSearchService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getRelevantDrugs(Model model, @RequestParam("searchstring") String searchstring) throws ParseException {
        List<Drug> relevantDrugs = drugSearchService.getRelevantDrugs(searchstring);
        Set<String> namesAlreadySeen = new HashSet<>();
        relevantDrugs.removeIf(p -> !namesAlreadySeen.add(p.getId()));
        Map<String, String> allDescriptions = new HashMap<>();
        Map<String, String> allDiseases = new HashMap<>();
        for(Drug eachDrug : relevantDrugs){
            char[] descriptionChars = eachDrug.getDescription();
            allDescriptions.put(eachDrug.getId(), new String(descriptionChars));
            allDiseases.put(eachDrug.getId(), String.join(", ", eachDrug.getDiseases()));
            LocalDate date = eachDrug.getReleased();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            eachDrug.setDateString(formatter.format(date));
        }
        model.addAttribute("allRelevantDrugs", relevantDrugs);
        model.addAttribute("allDescriptions", allDescriptions);
        model.addAttribute("allDiseases", allDiseases);
        model.addAttribute("searchString", searchstring);
        model.addAttribute("condition", true);
        return "index";
    }

    @GetMapping({"/","","/index.html","/index"})
    String getSearchView(Model model){
        List<Drug> relevantDrugs = new ArrayList<>();
        model.addAttribute("allRelevantDrugs", relevantDrugs);
        model.addAttribute("condition", false);
        return "index";
    }

}
