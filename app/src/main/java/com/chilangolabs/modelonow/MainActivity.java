package com.chilangolabs.modelonow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import io.conekta.conektasdk.Card;
import io.conekta.conektasdk.Conekta;
import io.conekta.conektasdk.Token;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Conekta.setPublicKey("key_AUpPUFrGdMGX2bMQrjQq41g"); //Set public key
//        Conekta.setApiVersion("1.0.0"); //Set api version (optional)
//        Conekta.collectDevice(this);
//
//        Card card = new Card("Josue Camara", "4242424242424242", "332", "11", "2017");
//        Token token = new Token(this);
//
//        //tok_8oW2KoJjCGZMaz2ie
//        //tok_Qtj7G2dQac1RNd3Wr
//        token.onCreateTokenListener(new Token.CreateToken() {
//            @Override
//            public void onCreateTokenReady(JSONObject data) {
//                try {
//                    //TODO: Create charge
//                    Log.d("Token::::", data.getString("id"));
//                    Log.d("Token::::", data.toString());
//                } catch (Exception err) {
//                    //TODO: Handle error
//                    Log.d("Error: ", err.toString());
//                }
//            }
//        });
//
//        token.create(card);

    }
}
