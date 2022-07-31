package Test;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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
import com.quanao.hanghieu.ui.ItemActivity;
import com.quanao.hanghieu.ui.LoginActivity;
import com.quanao.hanghieu.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeTest {
    UtilsTest utilsTest = new UtilsTest();
    @Rule
    public ActivityScenarioRule<TestActivity> activityRule =
            new ActivityScenarioRule<>(TestActivity.class);


    @Test
    public void testSearch() {
        try {
            Thread.sleep(10000);
            dangnhap();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            //utilsTest.equal(R.id.check_login,"sai tk hoặc mật khẩu");
            utilsTest.btnClick(R.id.search_view);
            sleep();
            utilsTest.setText(R.id.search_view, "name1");
            onView(withId(R.id.search_listView)).check(matches(isDisplayed()));
            sleep();
//            utilsTest.btnClick(R.id.search_view);
//            utilsTest.btnClick(R.id.search_view);
//            sleep();
            onData(anything()).inAdapterView(withId(R.id.search_listView)).atPosition(0)
                    .check(matches(isDisplayed()));

            sleep();
        }


    }
    @Test
    public void testAddtoCart() {
        try {
            Thread.sleep(10000);
            dangnhap();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            //utilsTest.equal(R.id.check_login,"sai tk hoặc mật khẩu");
            sleep();
            onData(anything()).inAdapterView(withId(R.id.categoriesGridFragment)).atPosition(0)
                    .perform(click());
            sleep();
            onData(anything()).inAdapterView(withId(R.id.gridItemView)).atPosition(0)
                    .perform(click());
            sleep();
            utilsTest.btnClick(R.id.btnPlus);
            utilsTest.btnClick(R.id.btnPlus);
            utilsTest.btnClick(R.id.btnDiv);
            utilsTest.btnClick(R.id.add_to_cart);
            utilsTest.pressBack();

            onData(anything()).inAdapterView(withId(R.id.gridItemView)).atPosition(1)
                    .perform(click());
            for (int i=0; i<9;i++) {
                utilsTest.btnClick(R.id.btnPlus);
            }
            utilsTest.btnClick(R.id.add_to_cart);
            sleep05();
            for (int i=0; i<9;i++) {
                utilsTest.btnClick(R.id.btnDiv);
            }
            utilsTest.btnClick(R.id.add_to_cart);
            utilsTest.pressBack();
            utilsTest.pressBack();




            sleep();
            utilsTest.btnClick(R.id.cartViewFragment);
            sleep();
            onData(anything()).inAdapterView(withId(R.id.cart_listView)).atPosition(0)
                    .check(matches(isDisplayed()));
            onData(anything()).inAdapterView(withId(R.id.cart_listView)).atPosition(1)
                    .onChildView(withId(R.id.btnHuy)).perform(click());
            utilsTest.btnClick(R.id.btnThanhToan);

            utilsTest.setText(R.id.address_thanhtoan,"test");
            utilsTest.setText(R.id.email_thanhtoan,"email_thanhtoan@gmail.com");
            utilsTest.setText(R.id.phone_thanhtoan,"0123456789");
            utilsTest.btnClick(R.id.buy_thanhtoan);
            try {
                Thread.sleep(10000);
            } catch (Exception e) {

            }


        }


    }


    void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
    }
    void sleep05() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {

        }
    }

    public void dangnhap() {
        utilsTest.setText(R.id.username_login, "admin");
        utilsTest.setText(R.id.password_login, "123");

        utilsTest.getListUser();

        utilsTest.btnClick(R.id.login_login);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            onView(withText("sai tk hoặc mật khẩu")).check(doesNotExist());

        }

    }

}
