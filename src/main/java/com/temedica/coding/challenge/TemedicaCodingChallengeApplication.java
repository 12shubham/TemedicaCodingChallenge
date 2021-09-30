package com.temedica.coding.challenge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.temedica.coding.challenge.entity.Drug;
import com.temedica.coding.challenge.entity.Drugs;
import com.temedica.coding.challenge.service.DrugSearchService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class TemedicaCodingChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemedicaCodingChallengeApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(DrugSearchService drugSearchService){
		return args -> {
			//read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			TypeReference<Drugs> typeReference = new TypeReference<Drugs>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/dataset.json");
			try {
				Drugs drugs = mapper.readValue(inputStream, typeReference);
				drugSearchService.loadAllDrugs(drugs.getDrugs());
				System.out.println("All Drugs loaded");
			} catch (IOException e){
				System.out.println("Unable to save all Drugs:" + e.getMessage());
			}
		};
	}
}
