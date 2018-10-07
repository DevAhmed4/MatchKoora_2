package matchkoora.devteam.com.matchkoora_;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Ref;

// this activity appears just for the first user use -- Slider View
public class Welcome extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout layoutdot;
    private TextView [] dotText;
    private int [] mLayouts;
    private Button nextBtn;
    private MySliderAdapter adapter;
    private TextView BigTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isFirstTimeStartApp()){
            //if not the first time to open the app --> go to home activity directly
            setFirstTimeStartStatus(false);
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }
        setStatuseBarTransparent();
        setContentView(R.layout.welcome_activity);
        InitComponent();



        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPage = viewPager.getCurrentItem()+1;
                if(currentPage < mLayouts.length){
                    //Move to next Page
                    viewPager.setCurrentItem(currentPage);
                }else{
                    setFirstTimeStartStatus(false);
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }
            }
        });

    }
    // set 3 dots for welcome screen
    private void setDotsStatus(int page) {

        layoutdot.removeAllViews();
        dotText = new TextView[mLayouts.length];
        for (int i = 0; i < dotText.length ; i++) {
            dotText[i] = new TextView(this);
            dotText[i].setText(Html.fromHtml("&#8226;"));
            dotText[i].setTextSize(30);
            dotText[i].setGravity(Gravity.BOTTOM);
            dotText[i].setTextColor(Color.parseColor("#a9b4bb"));
            layoutdot.addView(dotText[i]);
            //Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show();

        }
        if(dotText.length > 0){
            dotText[page].setTextColor(Color.parseColor("#ffffff"));
        }

    }

    private void setStatuseBarTransparent() {
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(ViewPager.SYSTEM_UI_FLAG_LAYOUT_STABLE  | View.SYSTEM_UI_FLAG_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void InitComponent() {
        mLayouts = new int[]{R.layout.slide1,R.layout.slide2,R.layout.slide3};
        adapter = new MySliderAdapter(mLayouts,getApplicationContext());
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        layoutdot = (LinearLayout)findViewById(R.id.dotLyout);
        nextBtn  = (Button)findViewById(R.id.btn_nxt);
        BigTextView =(TextView)findViewById(R.id.BigWelcomeText);
        setDotsStatus(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //change the text when ever slider change
                if(position == mLayouts.length-1){
                    nextBtn.setText(getResources().getText(R.string.start));
                    BigTextView.setText(getResources().getText(R.string.BigWelcomeText3));
                }else if(position == mLayouts.length-2){
                    nextBtn.setText(getResources().getText(R.string.next));
                    BigTextView.setText(getResources().getText(R.string.BigWelcomeText2));
                }else {
                    nextBtn.setText(getResources().getText(R.string.next));
                    BigTextView.setText(getResources().getText(R.string.BigWelcomeText));
                }
                setDotsStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private boolean isFirstTimeStartApp(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("WelcomeActivity", Context.MODE_PRIVATE);

        return pref.getBoolean("FirstTimeStartFlag",true);

    }

    private void setFirstTimeStartStatus(boolean stt){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("WelcomeActivity", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("FirstTimeStartFlag",stt);
        editor.commit();

    }
}
