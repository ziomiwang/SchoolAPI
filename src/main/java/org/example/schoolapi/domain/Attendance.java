package org.example.schoolapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    private Long id;

    private LocalDateTime entryDate;
    private LocalDateTime exitDate;

    @ManyToOne
    private Child child;
}
