package perk.appsaholic.bombdefusing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class HomeActivity extends ActionBarActivity {

    public static final String PREF_FILE_NAME = "defusersVisit";
    static int countVisit = 0;
    public static final String preferenceValue = "VisitedCount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //It is for saving the visiting count so when user came for secound time. He/She didn't go diectly to instuctions
        //because he/she knows the instuctions.
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(preferenceValue, "").equals("")){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(preferenceValue, countVisit + "");
            editor.apply();
        }


        if(sharedPreferences.getString(preferenceValue, "").equals("0")){
            startActivity(new Intent(HomeActivity.this, InstructionsActivity.class));
            finish();
        }
        else{
            Button startBtn = (Button) findViewById(R.id.start_btn);
            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                }
            });

            TextView instruction = (TextView) findViewById(R.id.instruction_text);
            instruction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(HomeActivity.this, InstructionsActivity.class));
                    finish();
                }
            });
        }
    }


}
