package com.fitness.backend.service;

import com.fitness.backend.entities.WorkoutSession;
import com.fitness.backend.repository.WorkoutSessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class WorkoutSessionService {
    private final WorkoutSessionRepository workoutSessionRepository;
    private final UserService userService;

    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository, UserService userService){
        this.workoutSessionRepository =  workoutSessionRepository;
        this.userService= userService;
    }

    @Transactional
    public List<WorkoutSession> findAll() {
        return workoutSessionRepository.findAll();
    }

    @Transactional
    public WorkoutSession findById(Long id) {
        return workoutSessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout session not found"));
    }

    @Transactional
    public WorkoutSession save(WorkoutSession workoutSession) {
        return workoutSessionRepository.save(workoutSession);
    }

    @Transactional
    public WorkoutSession update(Long id, WorkoutSession workoutSession) {
        WorkoutSession existingWorkoutSession = findById(id);
        existingWorkoutSession.setDescription(workoutSession.getDescription());
        existingWorkoutSession.setDate(workoutSession.getDate());
        existingWorkoutSession.setWorkoutDuration(workoutSession.getWorkoutDuration());
        return workoutSessionRepository.save(existingWorkoutSession);
    }

    @Transactional
    public void delete(Long id) {
        workoutSessionRepository.deleteById(id);
    }
}
