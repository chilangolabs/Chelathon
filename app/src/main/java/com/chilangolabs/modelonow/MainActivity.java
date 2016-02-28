package com.chilangolabs.modelonow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chilangolabs.modelonow.customwidgets.ButtonMontserrat;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ButtonMontserrat btnRegis, btnLogin;
    LoginButton btnFBLogin;
    View view;
    LinearLayout containerLogin;
    boolean isHide;
    CallbackManager callbackManager;
    private boolean regis = false;
    RequestQueue rq;
    SharedPreferences app_preference;
    SharedPreferences.Editor editor;

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

        rq = Volley.newRequestQueue(this);
        app_preference = this.getSharedPreferences(getString(R.string.preference_name), Context.MODE_PRIVATE);

        btnFBLogin.setReadPermissions("user_friends", "public_profile", "user_birthday");

        btnFBLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regis = true;
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null && !regis) {
            startActivity(new Intent(this, DrawerActivity.class)
            );
            finish();
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
                Log.e("token", loginResult.getAccessToken().getToken());
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("JSON", object.toString() + "");
                        try {
                            JSONObject json = new JSONObject();
                            json.put("name", object.getString("name"));
                            json.put("birthday", object.getString("birthday"));
                            json.put("mail", "");
                            json.put("phone", "");
                            json.put("password", "");
                            json.put("city", "");

                            String baseurl = getString(R.string.base_url);
                            String endpoint = getString(R.string.endpoint_register);

                            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, baseurl + endpoint, json, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        Log.e("token", response.getJSONObject("picture").getJSONObject("data").getString("url"));
                                        editor = app_preference.edit();
                                        editor.putString("authkey", response.getString("token"));
                                        editor.putString("profileUrl", response.getJSONObject("picture").getJSONObject("data").getString("url"));
                                        editor.putString("userName", Profile.getCurrentProfile().getFirstName());
                                        editor.putString("userLastName", Profile.getCurrentProfile().getMiddleName() + " " + Profile.getCurrentProfile().getLastName());
                                        editor.apply();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    startActivity(new Intent(MainActivity.this, DrawerActivity.class));
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });

                            rq.add(jsonRequest);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,birthday,email,cover,picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();
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
