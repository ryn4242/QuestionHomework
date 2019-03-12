package edu.psu.rjc65.questionhomework.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.psu.rjc65.questionhomework.R;

public class MainActivity extends AppCompatActivity {

    public Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButtonID);
    }

    public void startQuiz(View view){
        startActivity(new Intent(this, QuestionActivity.class));
    }

}
