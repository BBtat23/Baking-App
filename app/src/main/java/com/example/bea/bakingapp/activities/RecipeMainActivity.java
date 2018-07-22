package com.example.bea.bakingapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.bea.bakingapp.R;
import com.example.bea.bakingapp.adapter.RecipeAdapter;
import com.example.bea.bakingapp.data.Recipe;
import com.example.bea.bakingapp.fragment.RecipeFragment;
import com.example.bea.bakingapp.utils.JSONUtils;
import com.example.bea.bakingapp.utils.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

public class RecipeMainActivity extends AppCompatActivity {
    private String BASE_API = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    public static boolean twoPane = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            if (findViewById(R.id.tablet_layout) != null) {
                twoPane = true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                RecipeFragment recipeFragment = new RecipeFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.tablet_layout, recipeFragment)
                        .commit();
            } else {
                if (findViewById(R.id.phone_layout) != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    RecipeFragment recipeFragment = new RecipeFragment();
                    fragmentManager.beginTransaction()
                            .add(R.id.phone_layout, recipeFragment)
                            .commit();
                }
            }
        }
    }

}