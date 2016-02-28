package com.chilangolabs.modelonow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.cooltechworks.creditcarddesign.CardEditActivity;
import com.cooltechworks.creditcarddesign.CreditCardView;

public class UserInfoActivity extends AppCompatActivity {

    LinearLayout addCardLayout;
    CreditCardView creditCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        addCardLayout = (LinearLayout) findViewById(R.id.linearAddCard);
        creditCardView = (CreditCardView) findViewById(R.id.carduser);

        addCardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int GET_NEW_CARD = 2;

                Intent intent = new Intent(UserInfoActivity.this, CardEditActivity.class);
                startActivity(intent);
                startActivityForResult(intent, GET_NEW_CARD);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {

//            String cardHolderName = data.getStringExtra(CreditCardUtils.EXTRA_CARD_HOLDER_NAME);
//            String cardNumber = data.getStringExtra(CreditCardUtils.EXTRA_CARD_NUMBER);
//            String expiry = data.getStringExtra(CreditCardUtils.EXTRA_CARD_EXPIRY);
//            String cvv = data.getStringExtra(CreditCardUtils.EXTRA_CARD_CVV);

            creditCardView.setCardExpiry("10/22");
            creditCardView.setCVV("322");
            creditCardView.setCardNumber("4240102039200429");
            creditCardView.setCardHolderName("SR AAGUILERA");

            creditCardView.setVisibility(View.VISIBLE);
            addCardLayout.setVisibility(View.GONE);
        }
    }

}
