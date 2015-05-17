package perk.appsaholic.bombdefusing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class ScoringActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        TextView showScore = (TextView) findViewById(R.id.score_show);
        TextView instruction = (TextView) findViewById(R.id.scoring_instruction);
        Button playAgain = (Button) findViewById(R.id.play_again);
        Button home = (Button) findViewById(R.id.homeBtn);

        int score = getIntent().getIntExtra("score", 0);
        showScore.setText("Your Score is: "+ score);

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
