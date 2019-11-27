package at.javatraining.trainings;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public class SpringBaseMockTest extends SpringBaseTest {
    @Autowired
    protected MockMvc mockMVC;

    @Autowired
    protected ObjectMapper objectMapper;
}
