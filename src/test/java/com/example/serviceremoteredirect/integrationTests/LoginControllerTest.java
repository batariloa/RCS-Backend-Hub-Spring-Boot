package com.example.serviceremoteredirect.integrationTests;

import com.example.serviceremoteredirect.jwt.JWTUtility;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.regex.Pattern;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class LoginControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Autowired
    JWTUtility jwtUtility;


    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String password;




@Test
public void testAuthenticate() throws Exception {
    RequestBuilder request  = MockMvcRequestBuilders

            .post("/authenticate")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":  \""+username+
                    "\",  \"password\": \"" +password+"\" }");

              mvc.perform(request)
            .andExpect(status().isOk())
            .andDo(document("{methodName}"
                    , preprocessRequest(prettyPrint(),
                            replacePattern(Pattern.compile(username), "Entered username"),
                            replacePattern(Pattern.compile(password),"Entered password"))
                    ,preprocessResponse(prettyPrint())
                    ,responseFields(
                            fieldWithPath("jwtToken").description("token"),
                            fieldWithPath("ipAdress").description("IP adress of the client")
                    )))
            .andReturn();



}


} 
