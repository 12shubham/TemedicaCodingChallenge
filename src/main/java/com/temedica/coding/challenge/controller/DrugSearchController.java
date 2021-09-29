package com.temedica.coding.challenge.controller;

import com.temedica.coding.challenge.service.DrugSearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class DrugSearchController {

    private final DrugSearchService drugSearchService;

    @GetMapping(value = "search")
    public String getRelevantDrugs(Model model, @RequestParam("searchstring") String searchstring){
        model.addAttribute("allRelevantDrugs", drugSearchService.getRelevantDrugs(searchstring));
        return "searchDrugs";
    }

}
