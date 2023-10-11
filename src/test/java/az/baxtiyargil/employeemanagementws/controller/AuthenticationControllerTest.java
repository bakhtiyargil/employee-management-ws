package az.baxtiyargil.employeemanagementws.controller;

import az.baxtiyargil.employeemanagementws.security.config.SecurityConfig;
import az.baxtiyargil.employeemanagementws.security.model.JwtAuthenticationRequest;
import az.baxtiyargil.employeemanagementws.security.model.JwtAuthenticationResponse;
import az.baxtiyargil.employeemanagementws.security.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author bakhtiyargil on 09.05.2021
 */
@WebMvcTest(controllers = AuthenticationController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = SecurityConfig.class)})
class AuthenticationControllerTest {

    private final JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest("dummy",
            "dummy");
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthenticationService authenticationService;

    @Test
    void loginIsSuccess_and_returnsToken() throws Exception {

        //given
        String expectedToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbnRlcm4iLCJhdWQiOiJlbXBsb3llZS1h" +
                "cGktY2xpZW50cyIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaXNzIjoiS0FQSVRBTEJBTksiLCJleHAiOjE2MjA1ODg3NjUs" +
                "ImlhdCI6MTYyMDU4Nzg2NX0.B2MY76yo5JcduzhV6hbLsrXTW3dV4kuZXbPJrCO9zQsj3lkdWDW7IOzc6v85-k9GUquHMIKP" +
                "X156YjOBgxkCUA";
        JwtAuthenticationResponse expectedAnswer = new JwtAuthenticationResponse(expectedToken);

        String jsonRequestString = objectMapper.writeValueAsString(jwtAuthenticationRequest);
        String jsonResponseString = objectMapper.writeValueAsString(expectedAnswer);

        //when
        Mockito.when(authenticationService.createAuthenticationToken(jwtAuthenticationRequest))
                .thenReturn(expectedAnswer);

        ResultActions resultActions = mockMvc.perform(post("/api/auth/login")
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJh" +
                        "bGciOiJIUzUxMiJ9.eyJzdWIiOiJpbnRlcm4iLCJhdWQiOiJlbXBsb3llZS1hcGktY2xpZW50cyIsInJvbGVz" +
                        "IjpbIlJPTEVfQURNSU4iXSwiaXNzIjoiS0FQSVRBTEJBTksiLCJleHAiOjE2MjA1OTg3OTcsImlhdCI6MTYyMDU5Nzg" +
                        "5N30.vaNZW8A4jBX4MD8aVjjquNNlbBZEOeUC6i8DCQL-JdkN3Vpzh3hJg7jyZsclTWMd62SJO2W8QgK-YzOwlEN1Aw")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequestString));

        //then
        resultActions.andExpect(status().isOk()).andExpect(content().json(jsonResponseString));

        String resultAsJson = resultActions.andReturn().getResponse().getContentAsString();
        JwtAuthenticationResponse resultAnswer = objectMapper.readValue(resultAsJson, JwtAuthenticationResponse.class);
        assertThat(expectedAnswer).usingRecursiveComparison().isEqualTo(resultAnswer);

        Mockito.verify(authenticationService, times(1))
                .createAuthenticationToken(jwtAuthenticationRequest);
    }
}