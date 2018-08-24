package br.mobfeel.nutripet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.content.Intent;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

 //       getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent it = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(it);
                finish();

            }
        },2000);
    }
}
