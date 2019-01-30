package spring;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/1/22<br/>
 * Time: 14:39<br/>
 * To change this template use File | Settings | File Templates.
 * //@RunWith(SpringJUnit4ClassRunner.class)
 * //@ContextConfiguration(locations = {"classpath:spring.xml"})
 */

@SpringJUnitWebConfig(locations = "classpath:spring.xml")
public class TestControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * Nullpointer exception
     *
     * @throws Exception
     */
    @Test
    public void getAccount() throws Exception {
        this.mockMvc.perform(get("/test")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Lee"));
    }

}