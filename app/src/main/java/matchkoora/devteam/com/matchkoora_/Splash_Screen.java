package matchkoora.devteam.com.matchkoora_;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_Screen extends AppCompatActivity {
    private static final int SECONDS = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);



        /****** Create Thread that will sleep for 5 seconds****/
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 3 seconds
                    //start the animations for MTI_Logo image
                    //MTI_Logo.startAnimation(scaleDown);
                    sleep(SECONDS*1000);

                    // After 5 seconds redirect to another intent
                    Intent i=new Intent(getBaseContext(),Welcome.class);
                    startActivity(i);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }
}
