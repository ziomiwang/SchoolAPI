package org.example.schoolapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class School {
    @Id
    private Long id;

    private String name;
    private double hourPrice;
}
