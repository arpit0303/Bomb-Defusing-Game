package perk.appsaholic.bombdefusing;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.perk.perksdk.PerkManager;

import java.util.Random;


public class MainActivity extends Activity implements OnClickListener {

    ImageView mInstructor;
    TextView mSuggestion;
    TextView mScore;
    TextView mTimer;

    Random instructorRandom = new Random();
    Random suggestionRandom = new Random();
    int[] instructors = {R.drawable.police, R.drawable.thief};
    String[] suggestions = {"Press RED Button", "Press GREEN Button"};

    int mInstructorRandom = 0;
    int mSuggestionRandom = 0;
    static int score = 0;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PerkManager.startSession(MainActivity.this, "1fae721c1e63fcc879d8d2d18c68777696a43557");
        mScore = (TextView) findViewById(R.id.score);
        mTimer = (TextView) findViewById(R.id.time);
        mInstructor = (ImageView) findViewById(R.id.instructor);
        Button greenBtn = (Button) findViewById(R.id.green_btn);
        Button redBtn = (Button) findViewById(R.id.red_btn);
        mSuggestion = (TextView) findViewById(R.id.suggestion);

        mScore.setText(score + "");
        shuffle();
        setResources();

        greenBtn.setOnClickListener(this);
        redBtn.setOnClickListener(this);

        //For Timer
        countDownTimer = new CountDownTimer(60000,1000){

            @Override
            public void onTick(long l) {
                mTimer.setText((int) (l/1000)+ "");
            }

            @Override
            public void onFinish() {
                mTimer.setText("Over!!");
                PerkManager.showPortal(MainActivity.this, "1fae721c1e63fcc879d8d2d18c68777696a43557");
                score = 0;
                MainActivity.this.finish();
            }

        }.start();

    }

    //For generating random number for swicthing between poloce and thief
    //and the button to choose.
    private void shuffle() {
        mInstructorRandom = instructorRandom.nextInt(2);
        mSuggestionRandom = suggestionRandom.nextInt(2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.green_btn:
                String greenOutput = checkingOutput();
                if(greenOutput.equals("green")){
                    score += 5;
                    mScore.setText(score +"");
                }
                else{
                    score -= 5;
                    mScore.setText(score +"");
                }
                shuffle();
                shuffle();
                shuffle();
                setResources();
                break;

            case R.id.red_btn:
                String redOutput = checkingOutput();
                if(redOutput.equals("red")){
                    score += 5;
                    mScore.setText(score +"");
                }
                else{
                    score -= 5;
                    mScore.setText(score +"");
                }

                shuffle();
                shuffle();
                setResources();
                break;
            default:
                break;
        }
    }

    private void setResources() {
        mInstructor.setImageResource(instructors[mInstructorRandom]);
        mSuggestion.setText(suggestions[mSuggestionRandom]);
    }

    //It will give the correct output button to press
    private String checkingOutput(){
        if(mInstructorRandom == 0 && mSuggestionRandom == 0){
            return "red";
        }
        else if(mInstructorRandom == 0 && mSuggestionRandom == 1){
            return "green";
        }
        else if(mInstructorRandom == 1 && mSuggestionRandom == 0){
            return "green";
        }
        else if(mInstructorRandom == 1 && mSuggestionRandom == 1){
            return "red";
        }
        else{
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        score = 0;
        countDownTimer.onFinish();

    }
}
