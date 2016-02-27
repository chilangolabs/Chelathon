package com.chilangolabs.modelonow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cooltechworks.creditcarddesign.CardEditActivity;
import com.cooltechworks.creditcarddesign.CreditCardUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnAddCard);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int GET_NEW_CARD = 2;

                Intent intent = new Intent(MainActivity.this, CardEditActivity.class);
                startActivityForResult(intent, GET_NEW_CARD);
            }
        });
    }

    public void onActivityResult(int reqCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            String cardHolderName = data.getStringExtra(CreditCardUtils.EXTRA_CARD_HOLDER_NAME);
            String cardNumber = data.getStringExtra(CreditCardUtils.EXTRA_CARD_NUMBER);
            String expiry = data.getStringExtra(CreditCardUtils.EXTRA_CARD_EXPIRY);
            String cvv = data.getStringExtra(CreditCardUtils.EXTRA_CARD_CVV);

            Log.e("DataCard", cardHolderName + "\n"
                    + cardNumber + "\n"
                    + expiry + "\n"
                    + cvv + "\n");
        }
    }

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
