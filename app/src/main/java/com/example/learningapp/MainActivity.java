package com.example.learningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.learningapp.auth.LoginnActivity;
import com.example.learningapp.auth.RegisterActivity;
import com.example.learningapp.fragments.code.codeFragment;
import com.example.learningapp.fragments.home.HomeFragment;
import com.example.learningapp.fragments.quiz.quizFragment;
import com.example.learningapp.leaderBoard.LeaderBoardActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar_main);
//        bottomNavigationView.setOnNavigationItemSelectedListener(navSelect);
         navController = Navigation.findNavController(this,R.id.main_frame);
         NavigationUI.setupWithNavController(bottomNavigationView,navController);

        drawerLayout = findViewById(R.id.drawer_layout_main);
        navigationView = findViewById(R.id.navigation_view_main);
        auth = FirebaseAuth.getInstance();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

//        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new HomeFragment()).commit();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        if (item.getItemId() == R.id.menu_option_profile){
            Intent intent = new Intent(MainActivity.this, LeaderBoardActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener navSelect =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    Fragment selected = null;
//
//                    switch (item.getItemId()){
//
//                        case R.id.bottom_id_home:
//                            selected = new HomeFragment();
//                            break;
//                        case R.id.bottom_id_code:
//                            selected = new codeFragment();
//                            break;
//                        case R.id.bottom_id_quiz:
//                            selected = new quizFragment();
//                            break;
//
//                    }
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.main_frame,selected);
//                    fragmentTransaction.commit();
//                    return true;
//                }
//            };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.drawer_leader_board:
                startActivity(new Intent(MainActivity.this, LeaderBoardActivity.class));
                break;
            case R.id.drawer_book:
                startActivity(new Intent(MainActivity.this, BookActivity.class));
                break;
            case R.id.drawer_rate:
                signOut();
                break;
            case R.id.drawer_share:
                 signOut();
                break;
            case R.id.drawer_about:
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                break;

        }
        return true;
    }

    private void sendToLogIn(){
        startActivity(new Intent(MainActivity.this, LoginnActivity.class));
        finish();
    }

    private void signOut(){
        auth.signOut();
        sendToLogIn();
    }
}