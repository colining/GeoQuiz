package com.example.asus.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by asus on 2016/7/2.
 */
public class CheatActivity extends Activity {
    //private Button
    private TextView answertextview;
    private  Button showanswer;
    public static  final  String EXTRA_ANSWER_IS_TRUE="com.answer_is_true";
    private  boolean answeristrue;
    public  static  final String EXTA_ANSWER_SHOW="com.answer_shown";
    private void setanswershowResult(boolean isAnswerShown)
    {
        Intent data =new Intent();
        data.putExtra(EXTA_ANSWER_SHOW,isAnswerShown);
        setResult(RESULT_OK,data);
    }
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setanswershowResult(false);
        answeristrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        answertextview=(TextView)findViewById(R.id.answerTextView);
        showanswer=(Button)findViewById(R.id.showAnswerButton);
        showanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("test1231","lalaall");
                if(answeristrue)
                {
                    answertextview.setText(R.string.true_button);

                }
                else
                    answertextview.setText(R.string.false_button);
                setanswershowResult(true);
            }
        });
    }
}
