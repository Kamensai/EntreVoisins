package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by <Victor Khamvongsa> on <06/01/2021>
 */
public class NeighbourFavoriteFragment extends Fragment {
    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;


    /**
     * Create and return a new instance
     * @return @{@link NeighbourFavoriteFragment}
     */
    public static NeighbourFavoriteFragment newInstance() {
        NeighbourFavoriteFragment fragment = new NeighbourFavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("NeighbourFragment :: onCreate()");
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }


    /**
     * Init the List of favorite neighbours
     */
    private void initListOfFavorite() {
        mNeighbours = mApiService.getNeighbours();
        Iterator<Neighbour> iterator = mNeighbours.iterator();
        ArrayList<Neighbour> mListFavorite = new ArrayList();
            while (iterator.hasNext()) {
            Neighbour neighbour = iterator.next();
            if (neighbour.getIsFavorite() == true) {
                 mListFavorite.add(neighbour);
            }
        }
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mListFavorite, this.requireContext()));
    }


    @Override
    public void onResume() {
        super.onResume();
        System.out.println("NeighbourFragment :: onResume()");
        initListOfFavorite();
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("NeighbourFragment :: onStart()");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("NeighbourFragment :: onStop()");
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initListOfFavorite();
    }
}