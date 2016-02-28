package com.chilangolabs.modelonow.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.chilangolabs.modelonow.R;
import com.cooltechworks.creditcarddesign.CardEditActivity;
import com.cooltechworks.creditcarddesign.CreditCardUtils;
import com.cooltechworks.creditcarddesign.CreditCardView;

public class UserInfoFragment extends AppCompatActivity {

    LinearLayout addCardLayout;
    CreditCardView creditCardView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.fragment_user_info);
        addCardLayout = (LinearLayout) findViewById(R.id.linearAddCard);
        creditCardView = (CreditCardView) findViewById(R.id.carduser);

        addCardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int GET_NEW_CARD = 2;

                Intent intent = new Intent(UserInfoFragment.this, CardEditActivity.class);
                startActivity(intent);
                startActivityForResult(intent, GET_NEW_CARD);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {

            String cardHolderName = data.getStringExtra(CreditCardUtils.EXTRA_CARD_HOLDER_NAME);
            String cardNumber = data.getStringExtra(CreditCardUtils.EXTRA_CARD_NUMBER);
            String expiry = data.getStringExtra(CreditCardUtils.EXTRA_CARD_EXPIRY);
            String cvv = data.getStringExtra(CreditCardUtils.EXTRA_CARD_CVV);

            creditCardView.setCardExpiry(expiry);
            creditCardView.setCVV(cvv);
            creditCardView.setCardNumber(cardNumber);
            creditCardView.setCardHolderName(cardHolderName);

            creditCardView.setVisibility(View.VISIBLE);
            addCardLayout.setVisibility(View.GONE);
        }
    }
}