package at.javatraining.trainings.rest;

import at.javatraining.trainings.SpringBaseMockTest;
import at.javatraining.trainings.SpringBaseTest;
import at.javatraining.trainings.entities.Training;
import at.javatraining.trainings.repos.training.TrainingRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

class TrainingRestServiceTest extends SpringBaseMockTest {
    private static final String BASE_URL = "http://localhost:8090/api/trainings";

    @Value("#{springTraining}")
    Training training;

    @Autowired
    TrainingRepo trainingRepo;

    @Test
    void insert() throws Exception {
        Training trainingFromRest = saveTraining();
        assertNotNull(trainingFromRest.getId());
    }

    private Training saveTraining() throws Exception {
        String jsonTraining = objectMapper.writeValueAsString(training);

        var builder = MockMvcRequestBuilders.post(BASE_URL)
                .content(jsonTraining)
                .contentType(MediaType.APPLICATION_JSON);
        var response = mockMVC.perform(builder).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        return objectMapper.readValue(response.getContentAsString(), Training.class);
    }

    @Test
    void delete() throws Exception {
        Training trainingFromRest = saveTraining();
        assertTrue(trainingRepo.findById(trainingFromRest.getId()).isPresent());
        var builder = MockMvcRequestBuilders.delete(BASE_URL + "/" + trainingFromRest.getId());
        mockMVC.perform(builder).andReturn();
        assertTrue(trainingRepo.findById(trainingFromRest.getId()).isEmpty());
    }
}