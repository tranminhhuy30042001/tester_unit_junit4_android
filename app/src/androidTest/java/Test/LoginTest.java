package Test;


import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.quanao.hanghieu.R;

import com.quanao.hanghieu.ui.LoginActivity;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
    UtilsTest utilsTest = new UtilsTest();
    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testCase1(){
        utilsTest.setText(R.id.username_login,"zxcz");
        utilsTest.setText(R.id.password_login,"111");

        utilsTest.btnClick(R.id.login_login);
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            onView(withText("sai tk hoặc mật khẩu")).check(matches(isDisplayed()));
            //utilsTest.equal(R.id.check_login,"sai tk hoặc mật khẩu");

        }


    }


    @Test
    public void testCase2(){
        utilsTest.setText(R.id.username_login,"admin");
        utilsTest.setText(R.id.password_login,"123");

        utilsTest.getListUser();

        utilsTest.btnClick(R.id.login_login);


        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            onView(withText("sai tk hoặc mật khẩu")).check(doesNotExist());

        }

    }








}
