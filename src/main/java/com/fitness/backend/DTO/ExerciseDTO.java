package com.fitness.backend.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseDTO {
        private Long id;
        private String name;
        private int sets;
        private int reps;

}
