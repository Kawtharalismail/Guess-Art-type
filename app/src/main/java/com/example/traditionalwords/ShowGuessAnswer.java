package com.example.traditionalwords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.traditionalwords.MainActivity.mDrawableImages;
import static com.example.traditionalwords.MainActivity.random;

public class ShowGuessAnswer extends AppCompatActivity {

    TextView mAnswerTextView;
    ImageView mBackgroundImageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess_answer);
        mAnswerTextView=findViewById(R.id.answer_textView);
        mBackgroundImageview=findViewById(R.id.answer_backgroundimageview);
        Bundle extras = getIntent().getExtras();

        //String getAnswer=getIntent().getStringExtra("question_answer");
        mAnswerTextView.setText(extras.getString("question_answer"));
        mBackgroundImageview.setImageDrawable(ContextCompat.getDrawable(this,mDrawableImages[random]));

      //  Toast.makeText(ShowGuessAnswer.this,extras.getString("question_answer"),Toast.LENGTH_LONG).show();

    }
    public void BackToQuestions(View view){
        finish();
    }
}
