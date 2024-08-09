package com.fitness.backend.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class WorkoutSessionDTO {
    private Long id;
    private String date;
    private int workoutDuration;
    private int caloriesBurned;
    private List<ExerciseDTO> exercises;

}

