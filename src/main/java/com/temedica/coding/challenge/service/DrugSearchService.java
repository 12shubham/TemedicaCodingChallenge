package com.temedica.coding.challenge.service;

import com.temedica.coding.challenge.entity.Drug;

import java.util.List;

public interface DrugSearchService {
    public List<Drug> getRelevantDrugs(String drugOrDisease);
    public void loadAllDrugs(List<Drug> allDrugs);
}
