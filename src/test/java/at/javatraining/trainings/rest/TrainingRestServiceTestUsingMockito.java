package at.javatraining.trainings.rest;

import at.javatraining.trainings.SpringBaseMockTest;
import at.javatraining.trainings.entities.Training;
import at.javatraining.trainings.repos.training.TrainingRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainingRestServiceTestUsingMockito extends SpringBaseMockTest {
    private static final String BASE_URL = "/api/trainings";
    private static final Long ID_999 = 999L;

    @MockBean
    TrainingRepo trainingRepoMock;

    @Test
    void findById() throws Exception {
        Training training = Training.builder().id(ID_999).title("Test").build();
        Mockito.when(trainingRepoMock.findById(ID_999)).thenReturn(Optional.of(training));

        var builder = MockMvcRequestBuilders
                .get(BASE_URL + "/" + ID_999);
        String jsonString = mockMVC.perform(builder).andReturn().getResponse().getContentAsString();
        Training trainingFromRest = objectMapper.readValue(jsonString, Training.class);

        assertEquals("Test", trainingFromRest.getTitle());
    }
}
