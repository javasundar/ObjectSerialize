package com.binary.writer;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Belle on 2017/10/2.
 */
public class UTF8StringWriterTest {
    @Test
    public void write() throws Exception {
        UTF8StringWriter utf8StringWriter = new UTF8StringWriter();
        byte[] results = utf8StringWriter.write("ab", null);

        Assert.assertNotNull(results);
    }

}