package com.temedica.coding.challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="DRUGS")
public class Drug {
    @Id
    private String id;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> diseases = new HashSet<String>();
    @Lob
    private char[] description;
    private String name;
    private LocalDate released;
    @Transient
    @JsonIgnore
    private String dateString;
}
