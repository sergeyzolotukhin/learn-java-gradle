package ua.in.sz.spring.mvc.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(HomeController.class)
public class WebLayerTest implements ApplicationContextAware {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private BusinessService service;
    private ApplicationContext applicationContext;

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        when(service.getName()).thenReturn("Mock NAME");

        this.mockMvc.perform(get("/"))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Mock NAME")));

        log.info("\n\n\n");
        for (String name : Arrays.stream(applicationContext.getBeanDefinitionNames()).sorted().toList()) {
            log.info("Bean name: {}", name);
        }
        log.info("\n\n\n");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
