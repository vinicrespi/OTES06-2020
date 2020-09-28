package com.vinicius.chatandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.vinicius.chatandroid.R;
import com.vinicius.chatandroid.fragments.MessagemFragment;
import com.vinicius.chatandroid.models.ListaMensagem;
import com.vinicius.chatandroid.models.ListaUsuarios;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }
}
