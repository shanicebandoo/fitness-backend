package com.fitness.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness.backend.entities.WorkoutSession;
import com.fitness.backend.service.WorkoutSessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkoutSessionControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private WorkoutSessionService workoutSessionService;

    private MockMvc mockMvc;

    private WorkoutSession workoutSession;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        workoutSession = new WorkoutSession();
        workoutSession.setDate("2024-07-12");
        workoutSession.setWorkoutDuration(60);
        workoutSession.setCaloriesBurned(300);

        List<WorkoutSession> workoutSessionList = new ArrayList<>();
        workoutSessionList.add(workoutSession);

        Mockito.when(workoutSessionService.findAll()).thenReturn(workoutSessionList);
        Mockito.when(workoutSessionService.findById(1L)).thenReturn(workoutSession);
        Mockito.when(workoutSessionService.save(Mockito.any(WorkoutSession.class))).thenReturn(workoutSession);
        Mockito.when(workoutSessionService.update(Mockito.anyLong(), Mockito.any(WorkoutSession.class))).thenReturn(workoutSession);
    }

    @Test
    void getAllWorkoutSessions_validRequest_allWorkoutSessions() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/workout-sessions"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("2024-07-12"));
    }

    @Test
    void getWorkoutSessionById_validId_correctWorkoutSession() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/workout-sessions/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("300"));
    }

    @Test
    void deleteWorkoutSession_validRequest_workoutSessionDeleted() throws Exception {
        mockMvc.perform(delete("/api/workout-sessions/1"))
                .andExpect(status().isNoContent())
                .andReturn();

        Mockito.verify(workoutSessionService, Mockito.times(1)).delete(1L);
    }
}
