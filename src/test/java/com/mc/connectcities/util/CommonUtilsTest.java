package com.mc.connectcities.util;

import com.mc.connectcities.common.BaseTest;
import com.mc.connectcities.exception.ProcessingException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
public class CommonUtilsTest extends BaseTest{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Test
    public void testReadFileToArraylistSuccess() throws Exception {
        String[] expected = loadResource("city.txt").split("\r\n");
        ArrayList<String> actual = CommonUtils.readFileToArraylist("C:/test/city.txt");
        Assert.assertEquals(expected[0], actual.get(0));
        Assert.assertEquals(expected[1], actual.get(1));
        Assert.assertEquals(expected[2], actual.get(2));
        Assert.assertEquals(expected[3], actual.get(3));
    }

    @Test
    public void testReadFileToArraylistException() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Caught exception while trying to read file. C:\\test\\c.txt (The system cannot find the file specified)");
        CommonUtils.readFileToArraylist("C:/test/c.txt");
    }
}
