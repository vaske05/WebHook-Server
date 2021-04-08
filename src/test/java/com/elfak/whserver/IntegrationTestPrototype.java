package com.elfak.whserver;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
//@Transactional
//@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class IntegrationTestPrototype {

//    @Autowired
//    protected MockMvc mockMvc;
//    protected ObjectMapper objectMapper;

    @Before
    public void setup() {
//        objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
