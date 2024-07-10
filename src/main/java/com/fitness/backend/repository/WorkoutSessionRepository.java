package com.fitness.backend.repository;

import com.fitness.backend.entities.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
}
