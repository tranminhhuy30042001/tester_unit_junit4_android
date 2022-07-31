package Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static java.util.EnumSet.allOf;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.EspressoKey;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.fragment.SearchFragment;
import com.quanao.hanghieu.ui.HomeActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest {
    public static List<UserTest> lstUser = new ArrayList<>();

    private void addUser(UserTest userTest){
        String username = userTest.getUsername();
        String pass = userTest.getPassword();
        UserTest tmp = new UserTest(username,pass);
        lstUser.add(tmp);
    }
    private void addUser(String user ,String pass){
        UserTest tmp = new UserTest(user,pass);
        lstUser.add(tmp);
    }
    public void getListUser(){
        addUser(new UserTest("test1","111"));
        addUser(new UserTest("test2","222"));
        addUser(new UserTest("test3","333"));
        addUser(new UserTest("test4","444"));
    }


    public void setText(int idText,String value){
        onView(ViewMatchers.withId(idText)).perform(ViewActions.typeText(value),closeSoftKeyboard());
    }

    public void btnClick(int idBtn)
    {
        onView(withId(idBtn)).perform(ViewActions.click());
    }


    public void equal(int idText,String s)
    {
        onView(withId(idText)).check(matches(withText(s)));
    }
    public void textIsDisplay(String s)
    {
        onView(withText("sai tk hoặc mật khẩu")).check(matches(isDisplayed()));
    }
    public static void pressBack() {
        onView(isRoot()).perform(ViewActions.pressBack());
    }

    public static ViewAction typeSearchViewText(final String text){
        return new ViewAction(){
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Change view text";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((SearchView) view).setQuery(text,false);
            }
        };
    }
    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }


}
