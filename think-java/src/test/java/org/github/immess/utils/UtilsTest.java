package org.github.immess.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UtilsTest {

    @Test
    public void simpleTest() throws IOException {
        String data = "eurtfhdjge tjgh aieu43ht hfdgыфвпфвапфывапg ";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes());
        Assert.assertEquals(data, Utils.str(inputStream));
    }

    @Test
    public void stringSplitTest() {
        Map<String, String[]> testData = new HashMap<>();
        testData.put("first normal case", new String[]{"first","normal", "case"});
        testData.put("second \"normal case\"", new String[] {"second", "normal case"});
        testData.put("", new String[0]);
        testData.put("something wr\"ong case", new String[] {"something", "wr\"ong", "case"});
        testData.put("anoth\"er wrong\" case", new String[] {"anoth\"er", "wrong\"", "case"});
        testData.put("\"", new String[] {""});
        testData.put("\"\"test", new String[] {"", "test"});
        testData.put("really \"wrong case", new String[] {"really", "wrong case"});
        testData.put("i am stupid\"", new String[] {"i", "am", "stupid\""});
        testData.put("\"test case\" \"case test\" bebebe", new String[] {"test case", "case test", "bebebe"});
        testData.put("space,test", new String[] {"space,test"});
        testData.put("space  test", new String[] {"space", "test"});
        testData.put(" space test", new String[] {"space", "test"});
        testData.put("space test ", new String[] {"space", "test"});

        for (String key : testData.keySet()) {
            System.out.println(key);
            Assert.assertArrayEquals(testData.get(key), Utils.splitWithQuote(key,' ', '"'));
        }
    }
}
