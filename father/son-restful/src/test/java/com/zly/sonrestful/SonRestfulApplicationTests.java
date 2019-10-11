package com.zly.sonrestful;

import com.zly.sonrestful.controller.RestApiController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.equalTo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class SonRestfulApplicationTests {

    private MockMvc mvc;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new RestApiController()).build();
    }

    /**
     * 测试保存接口
     */
    @Test
    public void testInsert () throws Exception {
        RequestBuilder request = null;
        request = post("/rest/insert/")
                .param("id", "1")
                .param("name", "测试大师")
                .param("age", "20");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));
    }

    /**
     * 测试查询接口
     */
    @Test
    public void testSelect () throws Exception {
        RequestBuilder request = null;
        request = get("/rest/select/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));
    }

}
