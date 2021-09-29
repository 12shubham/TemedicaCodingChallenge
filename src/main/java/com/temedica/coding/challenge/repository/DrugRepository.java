package com.temedica.coding.challenge.repository;

import com.temedica.coding.challenge.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, String> {
    @Query(value = "SELECT * FROM DRUG WHERE NAME LIKE '%:1%' OR DISEASE LIKE '%:1%'", nativeQuery = true)
    public List<Drug> findDrugs(String drugOrDisease);
}
