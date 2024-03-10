//package com.development.scut_cdd;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//
//public class MyActivity extends AppCompatActivity {
//
//
//    private HandCardArea mHandCardArea;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mHandCardArea = findViewById(R.id.hand_card_area);
//        addCardsToHand();
//    }
//
//    private void addCardsToHand() {
//        for (int i = 0; i < 13; i++) {
//            CardPrefab card = new CardPrefab(this);
//            card.setImageResource(R.drawable.heitao05);
//            card.setPadding(10, 10, 10, 10);
//            card.setCardValue(i);
//            mHandCardArea.addView(card);
//        }
//    }
//}