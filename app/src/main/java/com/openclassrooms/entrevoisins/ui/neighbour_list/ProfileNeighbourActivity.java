package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

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
        mNeighbour = (Neighbour)profileNeighbourActivityIntent.getParcelableExtra(INTENT_NEIGHBOUR);

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

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean nei = mNeighbour.getIsFavorite();
                mNeighbour.setIsFavorite(!mNeighbour.getIsFavorite());
                Boolean neio = mNeighbour.getIsFavorite();
                loadFloatingFavoriteImageButton();
                mApiService.updateNeighbourFavorite(mNeighbour);
            }
        });
        loadFloatingFavoriteImageButton();
    }

    public void loadFloatingFavoriteImageButton(){
        if(mNeighbour.getIsFavorite()){
            mFavoriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_favorite_clicked));
        }
        else{
            mFavoriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_favorite_unclicked));
        }
    }

    public static Intent newInstance (Context context, Neighbour neighbour) {
        Intent profileNeighbourActivityIntent = new Intent(context, ProfileNeighbourActivity.class);
        profileNeighbourActivityIntent.putExtra(INTENT_NEIGHBOUR, neighbour); // récupérer l'utilisateur sur lequel on a cliqué
        return profileNeighbourActivityIntent;
    }

    // [Code sample – Store State in State Bundle]
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        // Store UI state to the savedInstanceState.
        // This bundle will be passed to onCreate on next call.  EditText txtName = (EditText)findViewById(R.id.txtName);

        boolean favorite = mNeighbour.getIsFavorite();

        savedInstanceState.putBoolean("Favorite", favorite);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        boolean favorite = savedInstanceState.getBoolean("Favorite");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Profile :: onResume()");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("Profile :: onStart()");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("Profile :: onStop()");
    }
}
