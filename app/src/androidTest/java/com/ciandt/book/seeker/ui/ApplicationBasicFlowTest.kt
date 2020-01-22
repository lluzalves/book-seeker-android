package com.ciandt.book.seeker.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.ciandt.book.seeker.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ApplicationBasicFlowTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun applicationBasicFlowTest() {
        val appCompatImageView = onView(
            allOf(
                withClassName(`is`("androidx.appcompat.widget.AppCompatImageView")),
                withContentDescription("Search"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.LinearLayout")),
                        childAtPosition(
                            withId(R.id.searchView),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val searchAutoComplete = onView(
            allOf(
                withId(`is`(R.id.searchView)),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.LinearLayout")),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(replaceText("Kotlin"), closeSoftKeyboard())

        val searchAutoComplete2 = onView(
            allOf(
                withId(`is`(R.id.searchView)),
                withText("Kotlin"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.LinearLayout")),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete2.perform(pressImeActionButton())

        val appCompatTextView = onView(
            allOf(
                withId(R.id.releaseDate), withText("2018-09-13"),
                childAtPosition(
                    allOf(
                        withId(R.id.itemLayout),
                        childAtPosition(
                            withClassName(`is`("androidx.cardview.widget.CardView")),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        val appCompatTextView2 = onView(
            allOf(
                withId(R.id.search), withText("Search"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.coordinator),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatTextView2.perform(click())

        val appCompatImageView2 = onView(
            allOf(
                withClassName(`is`("androidx.appcompat.widget.AppCompatImageView")),
                withContentDescription("Search"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.LinearLayout")),
                        childAtPosition(
                            withId(R.id.searchView),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView2.perform(click())

        val searchAutoComplete3 = onView(
            allOf(
                withId(`is`(R.id.searchView)),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.LinearLayout")),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete3.perform(replaceText("Java"), closeSoftKeyboard())

        val searchAutoComplete4 = onView(
            allOf(
                withId(`is`(R.id.searchView)),
                withText("Java"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.LinearLayout")),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete4.perform(pressImeActionButton())

        val constraintLayout = onView(
            allOf(
                withId(R.id.itemLayout),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(click())

        val appCompatTextView3 = onView(
            allOf(
                withId(R.id.appinfo), withText("Info"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.coordinator),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView3.perform(click())

        pressBack()
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
