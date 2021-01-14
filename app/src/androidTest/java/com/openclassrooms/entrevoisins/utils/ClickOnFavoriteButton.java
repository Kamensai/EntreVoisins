package com.openclassrooms.entrevoisins.utils;

import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

/**
 * Created by <Victor Khamvongsa> on <14/01/2021>
 */
public class ClickOnFavoriteButton implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View favoriteButton = view.findViewById(R.id.favorite_ActionButton);
        // Maybe check for null
        favoriteButton.performClick();
    }
}
