package com.fitness.backend.entities;

import jakarta.persistence.*;

import java.time.Duration;

@Entity
public class WorkoutSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String date;
    private Duration workoutDuration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Duration getWorkoutDuration() {
        return workoutDuration;
    }

    public void setWorkoutDuration(Duration workoutDuration) {
        this.workoutDuration = workoutDuration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
