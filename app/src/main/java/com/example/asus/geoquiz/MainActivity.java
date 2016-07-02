package com.example.asus.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mturebutton;
    private  Button mfalsebutton;
    private  ImageButton nextbutton;
    private  ImageButton prebutton;
    private TextView questiontextview;
    private  Button CheatButton;
    private boolean isCheater;

    private  static final String KEY_INDEX= "index";
    private  String TAG="GeoQuiz";
    private  TrueFalse[] questionbank= new  TrueFalse[]{
            new TrueFalse(R.string.question_oceans,true),
            new TrueFalse(R.string.question_mideast,true),
            new TrueFalse(R.string.question_africa,true),
            new TrueFalse(R.string.question_asia,true),
    };
    private void updatequestion(){
        int question =questionbank[mCurrentIndex].getmQuestion();
        questiontextview.setText(question);
    }
    private  void checkanswer(boolean userpressedtrue)
    {
        boolean answerIsTrue =questionbank[mCurrentIndex].ismTrueQuestion();
        int messageResId=0;
        if(isCheater){
            messageResId=R.string.judgment_toast;
        }
        else {
            if (userpressedtrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }
    private int mCurrentIndex=0;
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if (data==null)
        {
            return;
        }
        isCheater=data.getBooleanExtra(CheatActivity.EXTA_ANSWER_SHOW,false);
    }
    @Override
    protected  void  onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isCheater=false;
        questiontextview=(TextView)findViewById(R.id.question_text_view);
//        int question =questionbank[mCurrentIndex].getmQuestion();
//        questiontextview.setText(question);
        mfalsebutton =(Button)findViewById(R.id.false_button);
        mturebutton=(Button)findViewById(R.id.true_button);
        nextbutton=(ImageButton)findViewById(R.id.next_button);
        prebutton=(ImageButton)findViewById(R.id.Previous_button);
        questiontextview=(TextView)findViewById(R.id.question_text_view);
        CheatButton=(Button)findViewById(R.id.cheat_button);
        CheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean answeristrue=questionbank[mCurrentIndex].ismTrueQuestion();
                Intent i =new Intent(MainActivity.this,CheatActivity.class);
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answeristrue);
               // startActivity(i);
                startActivityForResult(i,0);
            }
        });
        prebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(mCurrentIndex==0)
//                {
//                    mCurrentIndex=questionbank.length-1;
//                }
//                else
//                {
//                    mCurrentIndex--;
//                }
              mCurrentIndex=(mCurrentIndex+questionbank.length-1)%(questionbank.length);
                //Log.d(TAG,"Updating question text for question #"+mCurrentIndex,new Exception());
                updatequestion();
            }
        });
        questiontextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%questionbank.length;
                updatequestion();
            }
        });
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%questionbank.length;
//                int question =questionbank[mCurrentIndex].getmQuestion();
//                questiontextview.setText(question);
updatequestion();
            }
        });
        mturebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT).show();
                checkanswer(true);
            }
        });
        mfalsebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
                checkanswer(false);
            }
        });

        if(savedInstanceState!=null)
        {
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        }
        updatequestion();
    }

}
