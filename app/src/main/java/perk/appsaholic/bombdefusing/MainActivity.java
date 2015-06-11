package perk.appsaholic.bombdefusing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends Activity implements View.OnTouchListener {

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

    Button greenBtn;
    Button redBtn;

    private int mInstructorRandom1 = 0;
    private int mSuggestionRandom1 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScore = (TextView) findViewById(R.id.score);
        mTimer = (TextView) findViewById(R.id.time);
        mInstructor = (ImageView) findViewById(R.id.instructor);
        greenBtn = (Button) findViewById(R.id.green_btn);
        redBtn = (Button) findViewById(R.id.red_btn);
        mSuggestion = (TextView) findViewById(R.id.suggestion);

        mScore.setText(" "+ score +" ");
        shuffle();
        setResources();

        greenBtn.setOnTouchListener(this);
        redBtn.setOnTouchListener(this);

        //For Timer
        countDownTimer = new CountDownTimer(20000,1000){

            @Override
            public void onTick(long l) {
                mTimer.setText(" "+ (int) (l/1000)+ " ");
            }

            @Override
            public void onFinish() {
                mTimer.setText("Over!!");
                Intent intent = new Intent(MainActivity.this, ScoringActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
                score = 0;
                MainActivity.this.finish();
                countDownTimer.cancel();
            }

        }.start();

    }

    //For generating random number for swicthing between poloce and thief
    //and the button to choose.
    private void shuffle() {
        mInstructorRandom1 = instructorRandom.nextInt(2);
        mSuggestionRandom1 = suggestionRandom.nextInt(2);

        while (mInstructorRandom == mInstructorRandom1 && mSuggestionRandom == mSuggestionRandom1 ){
            mInstructorRandom1 = instructorRandom.nextInt(2);
            mSuggestionRandom1 = suggestionRandom.nextInt(2);
        }

        mInstructorRandom = mInstructorRandom1;
        mSuggestionRandom = mSuggestionRandom1;
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
        countDownTimer.onFinish();
        countDownTimer.cancel();
        finish();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.green_btn:
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    String greenOutput = checkingOutput();
                    if (greenOutput.equals("green")) {
                        score += 10;
                        mScore.setText(score + "");
                    } else {
                        score -= 10;
                        mScore.setText(score + "");
                    }
                    shuffle();
                    setResources();
                    greenBtn.setAlpha(0.2f);
                } else {
                    greenBtn.setAlpha(1f);
                }
                return true;

            case R.id.red_btn:
                int action1 = event.getAction();
                if (action1 == MotionEvent.ACTION_DOWN) {
                    String redOutput = checkingOutput();
                    if (redOutput.equals("red")) {
                        score += 10;
                        mScore.setText(score + "");
                    } else {
                        score -= 10;
                        mScore.setText(score + "");
                    }
                    shuffle();
                    setResources();
                    redBtn.setAlpha(0.2f);
                } else {
                    redBtn.setAlpha(1f);
                }
                return true;
            default:
                return false;
        }
    }
}
