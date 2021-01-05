package com.openclassrooms.entrevoisins.service;

import android.view.View;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ProfileNeighbourActivity;

import java.util.List;

import androidx.fragment.app.Fragment;



/**
 * Created by <Victor Khamvongsa> on <17/12/2020>
 */
public class ClickOnNeighbourService extends NeighbourFragment implements NeighbourService {




    public void onItemClicked (View view, Neighbour neighbour) {


        // 2 - Show result in a Toast
        Toast.makeText(getContext(), "You clicked on neighbour : "+neighbour.getName(), Toast.LENGTH_SHORT).show();

        startActivity(ProfileNeighbourActivity.newInstance(view.getContext(), neighbour));
    }
}
