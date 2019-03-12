package edu.psu.rjc65.questionhomework.Activities;

import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import edu.psu.rjc65.questionhomework.Objects.Question;
import edu.psu.rjc65.questionhomework.R;

public class QuestionActivity extends AppCompatActivity {

    private String theQuestion;
    private TextView questionTextView;
    private RadioButton a1, a2, a3, a4;
    private ArrayList <Question> questionArrayList = new ArrayList<Question>();
    private RadioGroup answerGroup;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        String questionNumber = "Question One";

        answerGroup = findViewById(R.id.answerGroupID);

        /*try {
            theQuestion = findQuestion(questionNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    */

        try {
            createQuestionObjects();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.shuffle(questionArrayList);

        for(int i = 0; i < questionArrayList.size(); i++){
            Log.d("Number", Integer.toString(questionArrayList.get(i).getQuestionNumber()));
            Log.d("Question", questionArrayList.get(i).getQuestion());
            Log.d("A1", questionArrayList.get(i).getAnswerOne());
            Log.d("A2", questionArrayList.get(i).getAnswerTwo());
            Log.d("A3", questionArrayList.get(i).getAnswerThree());
            Log.d("A4", questionArrayList.get(i).getAnswerFour());
            Log.d("Correct Answer", questionArrayList.get(i).getCorrectAnswer());
        }

        /*if(theQuestion != null) {
            questionTextView.setText(theQuestion);
        } else {
            questionTextView.setText("Error");
        }
        */

        /*try {
            InputStream is = getAssets().open("easy_questions.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            questionTextView.setText(new String(buffer));

        } catch (IOException e) {
            e.printStackTrace();
        } */
    }

    @Override
    protected void onStart(){
        super.onStart();

        questionTextView = findViewById(R.id.QuestionTextViewID);
        a1 = findViewById(R.id.answerOneRadioButtonID);
        a2 = findViewById(R.id.answerTwoRadioButtonID);
        a3 = findViewById(R.id.answerThreeRadioButtonID);
        a4 = findViewById(R.id.answerFourRadioButtonID);

        questionTextView.setText(questionArrayList.get(0).getQuestion());
        a1.setText(questionArrayList.get(0).getAnswerOne());
        a2.setText(questionArrayList.get(0).getAnswerTwo());
        a3.setText(questionArrayList.get(0).getAnswerThree());
        a4.setText(questionArrayList.get(0).getAnswerFour());
    }

    public void nextButton(View view){
        int selectedID = answerGroup.getCheckedRadioButtonId();

    }

    public String findQuestion(String question) throws IOException{
        InputStream is = getAssets().open("easy_questions.txt");

        Scanner scan = new Scanner(is);

        while(scan.hasNext()) {
            String line = scan.nextLine();

            String[] pieces = line.split("=");

            if (pieces[0].equalsIgnoreCase(question.trim())) {
                return pieces[1];
            }
        }
        return null;
    }

    public void createQuestionObjects() throws IOException{
        int count = 0;
        String question = "", a1 = "", a2 = "", a3 = "", a4 = "", correct = "";
        int number = 0;

        InputStream is = getAssets().open("easy_questions.txt");

        Scanner scan = new Scanner(is);

        while(scan.hasNext()){
            String line = scan.nextLine();

            String[] pieces = line.split("=");

            switch (pieces[0]){

                case "Question":
                    question = pieces[1];
                    count++;
                    number = count;
                    break;
                case "Answer One":
                    a1 = pieces[1];
                    break;
                case "Answer Two":
                    a2 = pieces[1];
                    break;
                case "Answer Three":
                    a3 = pieces[1];
                    break;
                case "Answer Four":
                    a4 = pieces[1];
                    break;
                case "Correct Answer":
                    correct = pieces[1];
                    break;
                case "--":
                    questionArrayList.add(new Question(question, a1, a2, a3, a4, correct, number));
                    break;
            }
        }
    }
}
