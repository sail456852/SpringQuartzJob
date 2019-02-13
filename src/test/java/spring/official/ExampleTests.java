package spring.official;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import spring.dto.Pay;
import spring.utils.EmailUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 3.6.1. Server-Side Tests
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#spring-mvc-test-server
 * Problem I encountered:
 * Null exception -> add enough annotation
 * NotSuchMethodException ->  Spring version 4.3.8 -> 5.0.13
 * MissingResourceException -> add servlet api dependency
 * this test will load spring bean configuration
 * service bean will be loaded into IoC container.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:spring.xml")
public class ExampleTests {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private EmailUtils emailUtils;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getTest() throws Exception {
        this.mockMvc.perform(get("/test")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk());
    }

    @Test
    public void sendTemplateMail() {
        String msgBodyStr = "Java Test from Test Class";
        emailUtils.sendTemplateMail("sail456852@163.com", "sail456852@163.com", "java test", "pay-notshow", msgBodyStr);
    }
}