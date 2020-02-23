package com.example.cs449project;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.Tag;

import java.time.Instant;

public class LoginActivity  extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 0;
    private FirebaseAuth acc_auth;

    // UI references
    private AutoCompleteTextView acc_emailView;
    private EditText acc_passwordView;
    FirebaseUser acc_firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        acc_auth = FirebaseAuth.getInstance();

        acc_emailView = (AutoCompleteTextView) findViewById(R.id.email);
        acc_passwordView = (EditText) findViewById(R.id.login_password);

        acc_firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (acc_firebaseUser != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void reset(View view) {
        final EditText reset = new EditText(this);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Set title
        alertDialogBuilder.setTitle("Reset Email");
        // Set dialog message
        alertDialogBuilder
                .setMessage("Enter your email")
                .setCancelable(false)
                .setView(reset)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (reset.getText().toString().equals("")) {
                            Toast.makeText(LoginActivity.this, "Please type your email", Toast.LENGTH_SHORT).show();
                        } else {
                            acc_auth.sendPasswordResetEmail(reset.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(LoginActivity.this, "Please check your email box", Toast.LENGTH_SHORT).show();
                                    } else {
                                        String acc_error = task.getException().getMessage();
                                        Toast.makeText(LoginActivity.this, acc_error, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void Login(View view){
        // Reset
        acc_emailView.setError(null);
        acc_passwordView.setError(null);

        // Storing values at login
        String acc_email = acc_emailView.getText().toString();
        String acc_password = acc_passwordView.getText().toString();

        // Check for correct password
        if (acc_email.matches("")){
            Toast.makeText(this, "You need to enter an email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (acc_password.matches("")){
            Toast.makeText(this,"You need to enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isEmpty(acc_password) && !checkValidPassword(acc_password)){
            acc_passwordView.setError("Password must be at least 5 characters");
        }
        if (!checkValidEmail(acc_email) && !TextUtils.isEmpty(acc_email)){
            acc_emailView.setError("Your email is not correct");
        }

        acc_auth.signInWithEmailAndPassword(acc_email, acc_password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display message
                        // If sign in successful, then auth listener will handle log in

                        if(!task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    public boolean checkValidPassword(String acc_password) {
        return acc_password.length() >= 5;
    }

    public boolean checkValidEmail(String acc_email){
        return Patterns.EMAIL_ADDRESS.matcher(acc_email).matches();
    }

    public void redirectToSignUpPage(View view){
        Intent redirect = new Intent (LoginActivity.this, SignUpActivity.class);
        startActivity(redirect);
    }
}
