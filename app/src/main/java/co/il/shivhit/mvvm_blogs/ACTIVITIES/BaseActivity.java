package co.il.shivhit.mvvm_blogs.ACTIVITIES;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import co.il.shivhit.mvvm_blogs.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    protected abstract void initializeViews();
    protected abstract void setListeners();
}
