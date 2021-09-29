package com.temedica.coding.challenge.service;

import com.temedica.coding.challenge.entity.Drug;
import com.temedica.coding.challenge.repository.DrugRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DrugSearchServiceImpl implements DrugSearchService{

    private final DrugRepository drugRepository;

    @Override
    public List<Drug> getRelevantDrugs(String drugOrDisease) {
        return drugRepository.findDrugs(drugOrDisease);
    }

    @Override
    public void loadAllDrugs(List<Drug> allDrugs) {
        drugRepository.saveAll(allDrugs);
    }
}
