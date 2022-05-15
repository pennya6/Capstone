package com.example.basiccode;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.basiccode.databinding.FragementMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class DailyMainPage extends AppCompatActivity {
    private DailyMainFragment dailyMainFragment;
    private DailyMypageFragement dailyMypageFragement;
    private DailybuyingFragement dailybuyingFragement;
    private DailyStarFragement dailyStarFragement;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_main);

        dailyMainFragment=new DailyMainFragment();
        dailyMypageFragement=new DailyMypageFragement();
        dailybuyingFragement=new DailybuyingFragement();
        dailyStarFragement=new DailyStarFragement();

        toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("즐겨찾기");

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        System.out.println("user_id 메인페이지에서 잘 받음 " + user_id);

        Bundle bundle = new Bundle();
        bundle.putString("user_id", user_id + "");
        dailyStarFragement.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment, dailyStarFragement).commit();
        getSupportFragmentManager().beginTransaction().commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_reservation:
                        getSupportActionBar().setTitle("예약하기");
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,dailyMainFragment).commit();
                        return true;
                    case R.id.bottom_buying:

                        getSupportActionBar().setTitle("정기권구매");
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,dailybuyingFragement).commit();
                        return true;
                    case R.id.bottom_star:
                        getSupportActionBar().setTitle("즐겨찾기");
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,dailyStarFragement).commit();
                        return true;
                    case R.id.bottom_mypage:
                        getSupportActionBar().setTitle("마이페이지");
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,dailyMypageFragement).commit();
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.back:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
