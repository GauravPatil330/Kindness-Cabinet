package com.gauravpatil.kindnesscabinet;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.homeBottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.menuBottomNavHome);
    }
    HomeFragment homeFragment = new HomeFragment();
    CategoriesFragment categoriesFragment = new CategoriesFragment();
    AddFragment addFragment = new AddFragment();
    ChatsFragment chatsFragment = new ChatsFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        if(menuItem.getItemId() == R.id.menuBottomNavHome)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,homeFragment).commit();
        }
        else if(menuItem.getItemId() == R.id.menuBottomNavSearch)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout, categoriesFragment).commit();
        }
        else if(menuItem.getItemId() == R.id.menuBottomNavAdd)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,addFragment).commit();
        }
        else if(menuItem.getItemId() == R.id.menuBottomNavChats)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,chatsFragment).commit();
        }
        else if(menuItem.getItemId() == R.id.menuBottomNavProfile)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,profileFragment).commit();
        }
        return true;
    }
}