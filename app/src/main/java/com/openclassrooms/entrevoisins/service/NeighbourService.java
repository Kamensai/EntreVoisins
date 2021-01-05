package com.openclassrooms.entrevoisins.service;

import android.view.View;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Created by <Victor Khamvongsa> on <17/12/2020>
 */
public interface NeighbourService {

    void onItemClicked(View view, Neighbour neighbour);
}
