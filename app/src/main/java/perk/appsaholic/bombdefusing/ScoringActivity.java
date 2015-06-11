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


public class ScoringActivity extends ActionBarActivity implements View.OnClickListener{

    public static final String PREF_FILE_NAME = "bombSquad";
    public static final String HIGH_SCORE = "highScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        TextView showScore = (TextView) findViewById(R.id.score_show);
        TextView showHighScore = (TextView) findViewById(R.id.high_score_show);
        TextView instruction = (TextView) findViewById(R.id.scoring_instruction);
        Button playAgain = (Button) findViewById(R.id.play_again);
        Button home = (Button) findViewById(R.id.homeBtn);

        int score = getIntent().getIntExtra("score", 0);
        showScore.setText("Your Score is: "+ score);

        SharedPreferences sp = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        if(sp.getString(HIGH_SCORE, "").equals("")){
            showHighScore.setText("High Score : " + score);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(HIGH_SCORE, score + "");
            editor.apply();
        }
        else if(score > Integer.parseInt(sp.getString(HIGH_SCORE, ""))){

            showHighScore.setText("Congrats!!\n New High Score : " + score);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(HIGH_SCORE, score + "");
            editor.apply();
        }
        else{
            showHighScore.setText("High Score : "+ sp.getString(HIGH_SCORE, "0"));
        }

        instruction.setOnClickListener(this);
        playAgain.setOnClickListener(this);
        home.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play_again:
                startActivity(new Intent(ScoringActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.homeBtn:
                startActivity(new Intent(ScoringActivity.this, HomeActivity.class));
                finish();
                break;
            case R.id.scoring_instruction:
                startActivity(new Intent(ScoringActivity.this, InstructionsActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ScoringActivity.this, HomeActivity.class));
        finish();
    }
}
