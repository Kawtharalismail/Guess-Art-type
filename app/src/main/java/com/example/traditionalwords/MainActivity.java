package com.example.traditionalwords;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    static int[] mDrawableImages ;
    int mCurrentIndex=0;
    static int random;
    Random mRandomPlace;
    ImageView mBackgroundImage,mForegroudImage;
    String[] mArtTypes;
    String mCurrentArtType;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRandomPlace=new Random();
        mDrawableImages=new int[]{R.drawable.popart,R.drawable.abstractart,R.drawable.darksoualart,R.drawable.digitalart,R.drawable.mart,R.drawable.oilart,R.drawable.polygonart};
        mBackgroundImage=findViewById(R.id.art_backgroundimageview);
        mForegroudImage=findViewById(R.id.imageView2);
        Resources res = getResources();
        mArtTypes = res.getStringArray(R.array.ArtType);
        random = mRandomPlace.nextInt(mDrawableImages.length);
        mBackgroundImage.setImageDrawable(ContextCompat.getDrawable(this,mDrawableImages[random]));
        mForegroudImage.setImageDrawable(ContextCompat.getDrawable(this,mDrawableImages[random]));
        mCurrentArtType=mArtTypes[random];





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuChangeLanguage)
        {
            ShowLanguageDialog();
            return true;
        }else

            return super.onOptionsItemSelected(item);

    }

    private void ShowLanguageDialog() {

        AlertDialog dialog=new AlertDialog.Builder(this)
                .setTitle(R.string.change_language)
                .setItems(R.array.Language, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String language="en";
                        switch (which){
                            case 0:
                                language="en";
                                break;

                            case 1:
                                language="ar";
                                break;

                            case 2:
                                language="fa";
                                break;

                        }
                        LocalHelper.setLocale(MainActivity.this,language);
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).create();
        dialog.show();

    }

    public void ShowRandomImage(View view){

        int previouse=random;
        while (previouse==random)
        {  random = mRandomPlace.nextInt(mDrawableImages.length); }
        mBackgroundImage.setImageDrawable(ContextCompat.getDrawable(this, mDrawableImages[random]));
        mForegroudImage.setImageDrawable(ContextCompat.getDrawable(this, mDrawableImages[random]));
        mCurrentArtType=mArtTypes[random];


    }

    public void MoveToShowGuessAnswerActivity(View view){
        Intent answerIntent=new Intent(MainActivity.this,ShowGuessAnswer.class);
        answerIntent.putExtra("question_answer",mCurrentArtType);
        answerIntent.putExtra("current_image",random);
        startActivity(answerIntent);


    }
    public void ShareImage(View view){




        Uri imageUri = Uri.parse("android.resource://com.example.traditionalwords/drawable/"+ mDrawableImages[random]);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "send"));


    }


}
