package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


public class ProfileNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.neighbour_image)
    public ImageView mNeighbourImage;

    @BindView(R.id.back_Button)
    public ImageButton mBackButton;
    @BindView(R.id.favorite_ActionButton)
    public ImageButton mFavoriteButton;

    @BindView(R.id.neighbour_name)
    public TextView mNeighbourName;
    @BindView(R.id.neighbour_name2)
    public TextView mNeighbourName2;
    @BindView(R.id.neighbour_aboutMe_details)
    public TextView mAboutMe;
    @BindView(R.id.neighbour_address)
    public TextView mAddress;
    @BindView(R.id.neighbour_number)
    public TextView mPhoneNumber;
    @BindView(R.id.neighbour_facebook)
    public TextView mFacebook;

    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    private static final String INTENT_NEIGHBOUR = "neighbour";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getNeighbourApiService();

        setContentView(R.layout.activity_profil_neighbour);
        ButterKnife.bind(this);
        Intent profileNeighbourActivityIntent = getIntent();
        mNeighbour = (Neighbour) profileNeighbourActivityIntent.getParcelableExtra(INTENT_NEIGHBOUR);

        String neighbourName = mNeighbour.getName();
        String aboutMe = mNeighbour.getAboutMe();
        String address = mNeighbour.getAddress();
        String phoneNumber = mNeighbour.getPhoneNumber();
        String facebook = mNeighbour.getAvatarUrl();


        mNeighbourName.setText(neighbourName);
        mNeighbourName2.setText(neighbourName);
        mAboutMe.setText(aboutMe);
        mAddress.setText(address);
        mPhoneNumber.setText(phoneNumber);
        mFacebook.setText(facebook);


        Glide.with(mNeighbourImage.getContext())
                .load(mNeighbour.getAvatarUrl())
                .into(mNeighbourImage);

        mBackButton.setOnClickListener(view -> finish());

        mFavoriteButton.setOnClickListener(view -> {
            mNeighbour.setIsFavorite(!mNeighbour.getIsFavorite());
            mApiService.updateNeighbourFavorite(mNeighbour);
            loadFloatingFavoriteImageButton();
        });
        loadFloatingFavoriteImageButton();
    }

    public void loadFloatingFavoriteImageButton() {
        if (mNeighbour.getIsFavorite()) {
            Toast.makeText(getApplicationContext(), mNeighbour.getName() + " Is in your Favorites!", Toast.LENGTH_SHORT).show();
            mFavoriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_favorite_clicked));
        } else {
            mFavoriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_favorite_unclicked));
        }
    }

    public static Intent newInstance(Context context, Neighbour neighbour) {
        Intent profileNeighbourActivityIntent = new Intent(context, ProfileNeighbourActivity.class);
        profileNeighbourActivityIntent.putExtra(INTENT_NEIGHBOUR, neighbour); // récupérer l'utilisateur sur lequel on a cliqué grâce à une clé
        return profileNeighbourActivityIntent;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
