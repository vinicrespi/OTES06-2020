package com.vinicius.chatandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import com.vinicius.chatandroid.models.ListaUsuarios;
import com.vinicius.chatandroid.R;
import com.vinicius.chatandroid.fragments.ContatosFragment;
import com.vinicius.chatandroid.fragments.HomeFragment;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private AuthenticationPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
//        pagerAdapter.addFragment(new HomeFragment());
//        pagerAdapter.addFragment(new ContatosFragment());
//        pagerAdapter.addFragment(new MessageFragment());
        viewPager.setAdapter(pagerAdapter);

    }
    class AuthenticationPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragmentList = new ArrayList<>();

        //
        HomeFragment fragLogin     = new HomeFragment();
        ContatosFragment contatos   = new ContatosFragment();
//        Mensagens mensagens = new Mensagens();

        public AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentList.add(fragLogin);
            fragmentList.add(contatos);
            ListaUsuarios.getListaDeUsuarios().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(1);
                        }
                    };
                    runOnUiThread(r);

                }
            });
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

//        void addFragment(Fragment fragment) {
//            fragmentList.add(fragment);
//        }


    }
}