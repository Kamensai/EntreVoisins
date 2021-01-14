
package com.openclassrooms.entrevoisins.neighbour_list;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.android.material.tabs.TabItem;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ProfileNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.ClickOnFavoriteButton;
import com.openclassrooms.entrevoisins.utils.ClickOnTabFavorites;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.ProfileActivityViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FAVORITE_COUNT = 0;

    private ListNeighbourActivity mActivity;
    private ProfileNeighbourActivity mProfileNeighbourActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Rule
    public IntentsTestRule<ProfileNeighbourActivity> mProfileNeighbourActivityRule = new IntentsTestRule<>(ProfileNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mProfileNeighbourActivity = mProfileNeighbourActivityRule.getActivity();
        assertThat(mProfileNeighbourActivity,notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * When we click on an item, its details is launched in ProfileNeighbourActivity
     */
    @Test
    public void clickOnNeighbourToProfile () {
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new ProfileActivityViewAction()));
        Intents.init();
        intended(hasComponent(ProfileNeighbourActivity.class.getSimpleName()));
    }

    @Test
    public void neighbourNameIsNotEmpty () {
        String neighbourName = "Caroline";
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ProfileActivityViewAction()));
        onView(withId(R.id.neighbour_name)).check(matches(withText(neighbourName)));
    }

    @Test
    public void showOnlyFavorites() {
        // Clique sur l'onglet Favoris
        onView(withId(R.id.main_content)).perform(new ClickOnTabFavorites());
        // Liste de Favoris vide
        onView(withId(R.id.list_neighbours)).check(withItemCount(FAVORITE_COUNT));
        // Ajouter un Favoris à partir de l'écran profile activity
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ProfileActivityViewAction()));
        onView(withId(R.id.layout_profile_neighbour)).perform(new ClickOnFavoriteButton());
        //On retourne sur le main_content avec l'onglet Favoris
        onView(withId(R.id.main_content)).perform(new ClickOnTabFavorites());
        // Liste de Favoris avec 1 favoris
        onView(withId(R.id.list_neighbours)).check(withItemCount(FAVORITE_COUNT+1));
    }

}