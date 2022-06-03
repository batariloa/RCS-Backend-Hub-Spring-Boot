package com.example.serviceremoteredirect.integrationTests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class ApiIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Value("${admin.username}")
    private String username;

    @Test
    @WithMockUser(username = "SomeUser", password = "123456")
    public void integrationStatusTest() throws Exception {
        RequestBuilder request  = MockMvcRequestBuilders
                .get("/status?username="+username);

                mvc.perform(request)
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").exists())
              .andExpect(jsonPath("$.diskSpaceTotal").exists())
              .andExpect(jsonPath("$.diskSpaceUsable").exists())
              .andExpect(jsonPath("$.status").exists())
              .andDo(document("{methodName}"
                      ,preprocessRequest(prettyPrint())
                      ,preprocessResponse(prettyPrint())
                      ,responseFields(
                              fieldWithPath("id").description("Unique id of this status."),
                              fieldWithPath("diskSpaceTotal").description("Total space on the computer, presented in GB."),
                              fieldWithPath("diskSpaceUsable").description("Usable space on the computer, presented in GB."),
                              fieldWithPath("status").description("Short message that represents the current state of the computer."))))
              .andReturn();




    }


}
