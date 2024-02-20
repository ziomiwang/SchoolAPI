package org.example.schoolapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Child {

    @Id
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToOne
    private School school;

    @ManyToOne
    private Parent parent;
}
