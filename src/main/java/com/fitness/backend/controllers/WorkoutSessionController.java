package com.fitness.backend.controllers;

import com.fitness.backend.DTO.WorkoutSessionDTO;
import com.fitness.backend.entities.WorkoutSession;
import com.fitness.backend.service.WorkoutSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WorkoutSessionController {
    private final WorkoutSessionService workoutSessionService;

    @Autowired
    public WorkoutSessionController(WorkoutSessionService workoutSessionService) {
        this.workoutSessionService = workoutSessionService;
    }

//        GET

    @GetMapping("/workout-sessions")
    public ResponseEntity<List<WorkoutSession>> getAllWorkoutSessions() {
        List<WorkoutSession> sessions = workoutSessionService.findAll();
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/workout-sessions/{id}")
    public ResponseEntity<WorkoutSession> getWorkoutSessionById(@PathVariable Long id) {
        WorkoutSession session = workoutSessionService.findById(id);
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @GetMapping("/workout-sessions-with-exercises")
    public ResponseEntity<List<WorkoutSessionDTO>> getWorkoutSessionsWithExercises() {
        List<WorkoutSessionDTO> sessions = workoutSessionService.getAllWorkoutSessionsWithExercises();
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }
//       POST
    @PostMapping("/workout-sessions")
    public ResponseEntity<WorkoutSession> createWorkoutSession(@RequestBody WorkoutSession session) {
        WorkoutSession newSession = workoutSessionService.save(session);
        return new ResponseEntity<>(newSession, HttpStatus.CREATED);
    }

//        PUT
    @PutMapping("/workout-sessions/{id}")
    public ResponseEntity<WorkoutSession> updateWorkoutSession(@PathVariable Long id, @RequestBody WorkoutSession session) {
        WorkoutSession updatedSession = workoutSessionService.update(id, session);
        return new ResponseEntity<>(updatedSession, HttpStatus.OK);
    }
//       DELETE
    @DeleteMapping("/workout-sessions/{id}")
    public ResponseEntity<Void> deleteWorkoutSession(@PathVariable Long id) {
        workoutSessionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
