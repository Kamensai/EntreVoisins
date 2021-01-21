
package com.openclassrooms.entrevoisins.neighbour_list;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ProfileNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.ProfileActivityViewAction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParentIndex;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FAVORITE_COUNT = 0;


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    /**
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours), withParentIndex(0)))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(allOf(withId(R.id.list_neighbours), withParentIndex(0))).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), withParentIndex(0))).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours), withParentIndex(0))).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When we click on an item, its details is launched in ProfileNeighbourActivity
     */
    @Test
    public void clickOnNeighbourToProfile() {
        Intents.init();
        onView(allOf(withId(R.id.list_neighbours), withParentIndex(0)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new ProfileActivityViewAction()));
        intended(hasComponent(ProfileNeighbourActivity.class.getName()));
    }

    @Test
    public void neighbourNameIsNotEmpty() {
        String neighbourName = "Caroline";
        onView(allOf(withId(R.id.list_neighbours), withParentIndex(0)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ProfileActivityViewAction()));
        onView(withId(R.id.neighbour_name)).check(matches(withText(neighbourName)));
    }

    @Test
    public void showOnlyFavorites() {
        // Clique sur l'onglet Favoris
        onView(allOf(withText("Favorites"))).perform(ViewActions.click());

        // Liste de Favoris vide
        onView(allOf(withId(R.id.list_neighbours), withParentIndex(1))).check(withItemCount(FAVORITE_COUNT));

        // Clique sur l'onglet Mes voisins
        onView(allOf(withText("My neighbours"))).perform(ViewActions.click());

        // Ajouter un Favoris à partir de l'écran profile activity
        onView(allOf(withId(R.id.list_neighbours), withParentIndex(0)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ProfileActivityViewAction()));
        onView(withId(R.id.favorite_ActionButton)).perform(ViewActions.click());

        // On revient sur l'écran précédent
        onView(isRoot()).perform(ViewActions.pressBack());

        //On retourne sur le main_content avec l'onglet Favoris
        onView(allOf(withText("Favorites"))).perform(ViewActions.click());

        // Liste de Favoris avec 1 favoris
        onView(allOf(withId(R.id.list_neighbours), withParentIndex(1))).check(withItemCount(FAVORITE_COUNT + 1));
    }
}