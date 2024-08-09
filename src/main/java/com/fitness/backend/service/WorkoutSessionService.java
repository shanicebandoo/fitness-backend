package com.fitness.backend.service;

import com.fitness.backend.DTO.ExerciseDTO;
import com.fitness.backend.DTO.WorkoutSessionDTO;
import com.fitness.backend.entities.Exercise;
import com.fitness.backend.entities.WorkoutSession;
import com.fitness.backend.repository.ExerciseRepository;
import com.fitness.backend.repository.WorkoutSessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutSessionService {
    private WorkoutSessionRepository workoutSessionRepository;
    private ExerciseRepository exerciseRepository;
    private UserService userService;

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
        existingWorkoutSession.setDate(workoutSession.getDate());
        existingWorkoutSession.setWorkoutDuration(workoutSession.getWorkoutDuration());
        return workoutSessionRepository.save(existingWorkoutSession);
    }

    @Transactional
    public void delete(Long id) {
        workoutSessionRepository.deleteById(id);
    }

    public List<WorkoutSessionDTO> getAllWorkoutSessionsWithExercises() {
        List<WorkoutSession> sessions = workoutSessionRepository.findAll();
        return sessions.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public WorkoutSessionDTO mapToDTO(WorkoutSession session) {
        WorkoutSessionDTO dto = new WorkoutSessionDTO();
        dto.setId(session.getId());
        dto.setDate(session.getDate());
        dto.setWorkoutDuration(session.getWorkoutDuration());
        dto.setCaloriesBurned(session.getCaloriesBurned());
        dto.setExercises(session.getExercises().stream().map(this::mapExerciseToDTO).collect(Collectors.toList()));
        return dto;
    }

    public ExerciseDTO mapExerciseToDTO(Exercise exercise) {
        ExerciseDTO dto = new ExerciseDTO();
        dto.setId(exercise.getId());
        dto.setName(exercise.getExerciseName());
        dto.setSets(exercise.getSets());
        dto.setReps(exercise.getReps());
        return dto;
    }

}
