package UnitTest;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.xml.sax.Parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

@RunWith(JUnit4.class)
public class UnitTest {


    @Test
    public void testEmailValidity() {
        String testEmail = "tianhlalop1a@gmail.com";
        Assert.assertThat(String.format("Email Validity Test failed for %s ", testEmail),
                Data.checkEmailForValidity(testEmail), is(true));
    }
    @Test
    public void testEmailValidity2() {
        String testEmail = "   tianhlalop1asag@n samd  ";
        Assert.assertThat(String.format("Email Validity Test failed for %s ", testEmail),
                Data.checkEmailForValidity(testEmail), is(false));
    }
    @Test
    public void testEmailValidity3() {
        String testEmail = "   tianhlalop1asag@gmail  ";
        Assert.assertThat(String.format("Email Validity Test failed for %s ", testEmail),
                Data.checkEmailForValidity(testEmail), is(false));
    }
    @Test
    public void testEmailValidity4() {
        String testEmail = " tianhlalop1a@gmail.com ";
        Assert.assertThat(String.format("Email Validity Test failed for %s ", testEmail),
                Data.checkEmailForValidity(testEmail), is(false));
    }

    @Test
    public void editTextIsEmpty(){
        String s = "";
        Assert.assertThat(String.format("%s is not empty ", s),
                Data.isEmpty(s), is(true));
    }
    @Test
    public void editTextNotIsEmpty(){
        String s = "asd";
        Assert.assertThat(String.format("%s is not empty ", s),
                Data.isEmpty(s), is(false));
    }




}
