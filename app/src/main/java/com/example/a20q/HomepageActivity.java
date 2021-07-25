package com.example.a20q;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;

public class HomepageActivity extends AppCompatActivity {

    private TextView Display;
    private Button LogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Display = (TextView) findViewById(R.id.tvWelcome);
        LogOut = (Button) findViewById(R.id.btnBackToLogin);

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomepageActivity.this, MainActivity.class));
            }
        });
    }
}