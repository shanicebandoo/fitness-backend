package com.fitness.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String exerciseName;
    private int reps;
    private int sets;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "workout_session_id")
    @JsonBackReference
    private WorkoutSession workoutSession;
}
