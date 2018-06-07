package com.example.jokeshower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;

public class JokeShowerActivity extends AppCompatActivity {
    public static  final String INTENT_JOKE= "INTENT_JOKE";
    TextView jokeTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_shower);
        jokeTv = findViewById(R.id.joke_tv);

        Bundle bundle  =getIntent().getExtras();

        if (!bundle.isEmpty()){
            if (bundle.containsKey(INTENT_JOKE)){
                jokeTv.setText(bundle.getString(INTENT_JOKE));
            }

        }
    }
}
