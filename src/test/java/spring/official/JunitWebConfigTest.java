package spring.official;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *  Why is this null pointer ? not loaded !
 *   Test Class should be from jupiter package & @BeforeEach should
 *  also come from the same package
 */
@SpringJUnitWebConfig(locations = "classpath:spring.xml")
public class JunitWebConfigTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getAccount() throws Exception {
        this.mockMvc.perform(get("/test")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk());
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$.name").value("Lee"));
    }
}