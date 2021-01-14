package com.openclassrooms.entrevoisins.utils;

import android.content.ClipData;
import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

/**
 * Created by <Victor Khamvongsa> on <13/01/2021>
 */
public class ProfileActivityViewAction implements ViewAction {
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
        View itemView = view.findViewById(R.id.item_neighbour);
        // Maybe check for null
        itemView.performClick();
    }
}
