package com.ichi3.anki;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.*;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@SuppressWarnings("deprecation")
@RunWith(AndroidJUnit4.class)
public class AddCardTest {

    @Rule
    public ActivityTestRule<IntentHandler> mActivityTestRule = new ActivityTestRule<>(IntentHandler.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void addCardTest() {
        ViewInteraction viewInteraction = onView(
                allOf(withId(R.id.fab_expand_menu_button), withContentDescription("Add"),
                        childAtPosition(
                                allOf(withId(R.id.add_content_menu),
                                        childAtPosition(
                                                withId(R.id.root_layout),
                                                2)),
                                3),
                        isDisplayed()));
        viewInteraction.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.add_note_action), withContentDescription("Add"),
                        childAtPosition(
                                allOf(withId(R.id.add_content_menu),
                                        childAtPosition(
                                                withId(R.id.root_layout),
                                                1)),
                                5),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.add_shared_action), withContentDescription("Get shared decks"),
                        childAtPosition(
                                allOf(withId(R.id.add_content_menu),
                                        childAtPosition(
                                                withId(R.id.root_layout),
                                                1)),
                                3),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.add_shared_action), withContentDescription("Get shared decks"),
                        childAtPosition(
                                allOf(withId(R.id.add_content_menu),
                                        childAtPosition(
                                                withId(R.id.root_layout),
                                                1)),
                                3),
                        isDisplayed()));
        imageButton3.check(matches(isDisplayed()));

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.add_deck_action), withContentDescription("Create deck"),
                        childAtPosition(
                                allOf(withId(R.id.add_content_menu),
                                        childAtPosition(
                                                withId(R.id.root_layout),
                                                1)),
                                1),
                        isDisplayed()));
        imageButton4.check(matches(isDisplayed()));

        ViewInteraction imageButton5 = onView(
                allOf(withId(R.id.add_deck_action), withContentDescription("Create deck"),
                        childAtPosition(
                                allOf(withId(R.id.add_content_menu),
                                        childAtPosition(
                                                withId(R.id.root_layout),
                                                1)),
                                1),
                        isDisplayed()));
        imageButton5.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_note_action), withContentDescription("Add"),
                        childAtPosition(
                                allOf(withId(R.id.add_content_menu),
                                        childAtPosition(
                                                withId(R.id.root_layout),
                                                2)),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction spinner = onView(
                allOf(withId(R.id.note_type_spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardEditorLayout),
                                        0),
                                1),
                        isDisplayed()));
        spinner.check(matches(isDisplayed()));

        ViewInteraction spinner2 = onView(
                allOf(withId(R.id.note_deck_spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardEditorLayout),
                                        1),
                                1),
                        isDisplayed()));
        spinner2.check(matches(isDisplayed()));

        ViewInteraction editText = onView(
                allOf(withId(R.id.id_note_editText), withContentDescription("Front"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardEditorEditFieldsLayout),
                                        1),
                                0),
                        isDisplayed()));
        editText.check(matches(withText("")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.id_note_editText), withContentDescription("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardEditorEditFieldsLayout),
                                        3),
                                0),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.id_note_editText), withContentDescription("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardEditorEditFieldsLayout),
                                        3),
                                0),
                        isDisplayed()));
        editText3.check(matches(isDisplayed()));

        ViewInteraction fieldEditText = onView(
                allOf(withId(R.id.id_note_editText), withContentDescription("Front"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardEditorEditFieldsLayout),
                                        1),
                                0)));
        fieldEditText.perform(scrollTo(), click());

        ViewInteraction fieldEditText2 = onView(
                allOf(withId(R.id.id_note_editText), withContentDescription("Front"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardEditorEditFieldsLayout),
                                        1),
                                0)));
        fieldEditText2.perform(scrollTo(), click());

        ViewInteraction fieldEditText3 = onView(
                allOf(withId(R.id.id_note_editText), withContentDescription("Front"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardEditorEditFieldsLayout),
                                        1),
                                0)));
        fieldEditText3.perform(scrollTo(), replaceText("Question"), closeSoftKeyboard());

        ViewInteraction fieldEditText4 = onView(
                allOf(withId(R.id.id_note_editText), withContentDescription("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardEditorEditFieldsLayout),
                                        3),
                                0)));
        fieldEditText4.perform(scrollTo(), replaceText("Answer"), closeSoftKeyboard());

        pressBack();

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_save), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        4),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction scrollView = onView(
                allOf(withId(R.id.CardEditorScroll),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.root_layout),
                                        0),
                                1),
                        isDisplayed()));
        scrollView.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
