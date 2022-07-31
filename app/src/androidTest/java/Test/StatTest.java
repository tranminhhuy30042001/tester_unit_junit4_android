package Test;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static org.hamcrest.CoreMatchers.anything;

import android.app.Activity;
import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.TestActivity;
import com.quanao.hanghieu.service.APIHeroku;
import com.quanao.hanghieu.ui.ItemActivity;
import com.quanao.hanghieu.ui.LoginActivity;
import com.quanao.hanghieu.ui.MainActivity;
import com.quanao.hanghieu.ui.ManagerActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StatTest {
    UtilsTest utilsTest = new UtilsTest();
    @Rule
    public ActivityScenarioRule<ManagerActivity> activityRule =
            new ActivityScenarioRule<>(ManagerActivity.class);



    @Test
    public void testStat() {
        try {
            Thread.sleep(10000);
            onData(anything()).inAdapterView(withId(R.id.managerList)).atPosition(0)
                    .check(matches(isDisplayed()));

            onData(anything()).inAdapterView(withId(R.id.managerList)).atPosition(0)
                    .perform(click());
            sleep();
            utilsTest.btnClick(R.id.btnAccept);
            sleep();
            utilsTest.btnClick(R.id.btnThongKe);
            sleep10();
            onData(anything()).inAdapterView(withId(R.id.lstThongKe)).atPosition(0)
                    .perform(click());

            onData(anything()).inAdapterView(withId(R.id.listDetailManager)).atPosition(0)
                    .onChildView(withId(R.id.itemTextPrice_h)).check(matches(withText("$11")));
            sleep();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            //utilsTest.equal(R.id.check_login,"sai tk hoặc mật khẩu");

        }


    }
    void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
    }
    void sleep10() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {

        }
    }


}
