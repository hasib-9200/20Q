package com.example.a20q;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText getEmail;
    private EditText getPass;
    private Button Register;
    private Button SignOut;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getEmail = (EditText) findViewById(R.id.etRegEmail);
        getPass = (EditText) findViewById(R.id.etRegPass);
        Register = (Button) findViewById(R.id.btnReg);
        SignOut = (Button) findViewById(R.id.btnRegToLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String email = getEmail.getText().toString().trim();
                    String pass = getPass.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private Boolean validate() {
        String email = getEmail.getText().toString();
        String pass = getPass.getText().toString();

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(RegistrationActivity.this, "Please enter all fields.", Toast.LENGTH_LONG).show();
        } else {
            int passLen = pass.length();
            Boolean up = false;
            Boolean low = false;
            Boolean num = false;

            for (int i = 0; i < passLen; i++) {
                if (pass.charAt(i) >= 65 && pass.charAt(i) <= 90) up = true;
                if (pass.charAt(i) >= 97 && pass.charAt(i) <= 122) low = true;
                if (pass.charAt(i) >= 48 && pass.charAt(i) <= 57) num = true;
                if (up && low && num) return true;
            }
            if (up && low && num && passLen >= 8) return true;
            else {
                Toast.makeText(RegistrationActivity.this, "Password must need to meet requirements", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return false;
    }
}