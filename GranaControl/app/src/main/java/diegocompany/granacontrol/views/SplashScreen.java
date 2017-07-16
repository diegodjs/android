package diegocompany.granacontrol.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import diegocompany.granacontrol.R;
import diegocompany.granacontrol.utils.ActivityUtil;

public class SplashScreen extends ActivityUtil {

    private Intent intent = null;
    private static int SPLASH_TIME_OUT = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {

                if (profile != null) {
                    intent = new Intent(getContext(), Diario.class);
                    startActivity(intent);
                }
                else {
                    intent = new Intent(getContext(), Login.class);
                    startActivity(intent);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}