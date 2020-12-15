package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

public class ProfileNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.neighbour_image)
    public ImageView mNeighbourImage;
    @BindView(R.id.neighbour_name)
    public TextView mNeighbourName;
    @BindView(R.id.neighbour_name2)
    public TextView mNeighbourName2;

    private static final String INTENT_NEIGHBOUR = "neighbour";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_neighbour);
        ButterKnife.bind(this);
        Intent profileNeighbourActivityIntent = getIntent();
        Neighbour mNeighbour = (Neighbour)profileNeighbourActivityIntent.getParcelableExtra(INTENT_NEIGHBOUR);

        String neighbourName = mNeighbour.getName();
        Glide.with(mNeighbourImage.getContext())
                .load(mNeighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(mNeighbourImage);

        mNeighbourName.setText(neighbourName);
        mNeighbourName2.setText(neighbourName);

    }

    public static Intent newInstance (Context context, Neighbour neighbour) {
        Intent profileNeighbourActivityIntent = new Intent(context, ProfileNeighbourActivity.class);
        profileNeighbourActivityIntent.putExtra(INTENT_NEIGHBOUR, neighbour); // récupérer l'utilisateur sur lequel on a cliqué
        return profileNeighbourActivityIntent;
    }

}
