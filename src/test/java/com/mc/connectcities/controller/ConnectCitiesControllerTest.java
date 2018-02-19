package com.mc.connectcities.controller;

import com.mc.connectcities.logic.ConnectCitiesLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConnectCitiesControllerTest {
    private MockMvc mockMvc;
    private ConnectCitiesController connectCitiesControllerUnderTest;
    @Mock
    public ConnectCitiesLogic connectCitiesLogic;

    protected MockMvc getMockMvc() {
        return this.mockMvc;
    }

    @Before
    public void setup() {
        connectCitiesControllerUnderTest = new ConnectCitiesController(connectCitiesLogic);
        mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.connectCitiesControllerUnderTest}).build();
    }

    @Test
    public void testConnectedNoOrigin() throws Exception {
        getMockMvc().perform(get("/connected?destination=a"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testConnectedNoDestination() throws Exception {
        getMockMvc().perform(get("/connected?origin=a"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testConnectedNoRequestParam() throws Exception {
        getMockMvc().perform(get("/connected"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testConnectedGoodrequest() throws Exception {
        when(connectCitiesLogic.connected(anyString(), anyString())).thenReturn("yes");
        MvcResult mvcResult = getMockMvc().perform(get("/connected?origin=a&destination=b"))
                .andExpect(status().isOk()).andReturn();
        assertEquals("yes", mvcResult.getResponse().getContentAsString());
    }
}

