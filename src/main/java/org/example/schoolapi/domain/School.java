package org.example.schoolapi.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private List<Child> childs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private List<Parent> parents;
}
