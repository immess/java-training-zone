package org.github.immess.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class UtilsTest {

    @Test
    public void simpleTest() throws IOException {
        String data = "eurtfhdjge tjgh aieu43ht hfdgыфвпфвапфывапg ";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes());
        Assert.assertEquals(data, Utils.str(inputStream));
    }
}
