package com.temedica.coding.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drug {
    @Id
    private String id;
    private String[] diseases;
    private String description;
    private String name;
    private String released;
}
