package com.chilangolabs.modelonow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chilangolabs.modelonow.customwidgets.ButtonMontserrat;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    ButtonMontserrat btnRegis, btnLogin;
    LoginButton btnFBLogin;
    View view;
    LinearLayout containerLogin;
    boolean isHide;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        btnFBLogin = (LoginButton) findViewById(R.id.btnFbLogin);
        btnRegis = (ButtonMontserrat) findViewById(R.id.btnRegist);
        btnLogin = (ButtonMontserrat) findViewById(R.id.btnLogin);
        view = findViewById(R.id.viewLine);
        containerLogin = (LinearLayout) findViewById(R.id.linearContainerLogin);
        callbackManager = CallbackManager.Factory.create();

        btnFBLogin.setReadPermissions("user_friends");

    }


    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            startActivity(new Intent(this, DrawerActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TASK
                            | Intent.FLAG_ACTIVITY_NO_HISTORY));
//            Toast.makeText(this, "the token is" + accessToken.getToken(), Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(this, "no have token", Toast.LENGTH_SHORT).show();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                containerLogin.setVisibility(View.VISIBLE);
                btnRegis.setVisibility(View.GONE);
                isHide = true;
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("SuccesLoginManager", loginResult.toString());
                Log.e("token", loginResult.getAccessToken().getToken());
                Log.e("userID", loginResult.getAccessToken().getUserId());
                Log.e("User name", Profile.getCurrentProfile().getFirstName() + " " + Profile.getCurrentProfile().getLastName());
            }

            @Override
            public void onCancel() {
                Log.e("CancelLoginManager", "cancel!");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("ErrorLoginManager", error.toString());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onBackPressed() {
        if (isHide) {
            containerLogin.setVisibility(View.GONE);
            btnRegis.setVisibility(View.VISIBLE);
            isHide = false;
        } else {
            super.onBackPressed();
        }
    }
}
