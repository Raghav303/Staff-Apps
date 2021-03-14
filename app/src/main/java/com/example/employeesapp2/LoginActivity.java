package com.example.employeesapp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

//Activity for user Authentification
public class LoginActivity extends AppCompatActivity {

    private static final int AUTHUI_REQUEST_CODE = 101;
    public static final String TAG = "Login Activity";
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }

            login = findViewById(R.id.button);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build());

                    Intent intent = AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setTosAndPrivacyPolicyUrls("https://example.com", "https://example.com")
                            .setLogo(R.drawable.logo)
                            .setAlwaysShowSignInMethodScreen(true)
                            .setIsSmartLockEnabled(false)
                            .build();

                    startActivityForResult(intent, AUTHUI_REQUEST_CODE);
                }
            });
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTHUI_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();


            } else {
                // Signing in failed
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if (response == null) {
                    Log.d(TAG, "onActivityResult: the user has cancelled the sign in request");
                } else {
                    Log.e(TAG, "onActivityResult: ", response.getError());
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}