package com.mc.connectcities.logic;

import com.mc.connectcities.exception.ProcessingException;
import com.mc.connectcities.util.CommonUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest(CommonUtils.class)
public class ConnectCitiesLogicTest {
    //ArrayList<String> data = new ArrayList<>();
    ConnectCitiesLogic connectCitiesLogic;

    @Before
    public void setup() throws ProcessingException {
        //data = CommonUtils.readFileToArraylist("C:/test/city.txt");
        connectCitiesLogic = new ConnectCitiesLogic("/src/test/resources/city.txt");
        PowerMockito.mockStatic(CommonUtils.class);
    }

    @Test
    public void testConnectedForSuccess() throws ProcessingException {
        when(CommonUtils.readFileToArraylist(anyString())).thenCallRealMethod();
        assertEquals("yes", connectCitiesLogic.connected("Boston", "Newark"));
        assertEquals("yes", connectCitiesLogic.connected("Boston", "Philadelphia"));
        assertEquals("no", connectCitiesLogic.connected("Philadelphia", "Albany"));
        assertEquals("yes", connectCitiesLogic.connected("Newark", "A"));
        assertEquals("yes", connectCitiesLogic.connected("Newark", "New York"));
        assertEquals("no", connectCitiesLogic.connected("B", "Trenton"));
    }

    @Test
    public void testConnectedForNoDataInFile() throws ProcessingException {
        when(CommonUtils.readFileToArraylist(anyString())).thenReturn(new ArrayList<>());
        assertEquals("No data found in file", connectCitiesLogic.connected("Boston", "Newark"));
    }

    @Test
    public void testConnectedForBadOrigin() throws ProcessingException {
        when(CommonUtils.readFileToArraylist(anyString())).thenCallRealMethod();
        assertEquals("Origin is not part of data", connectCitiesLogic.connected("Mumbai", "Newark"));
    }
}
