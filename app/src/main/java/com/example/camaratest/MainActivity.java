package com.example.camaratest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button trigger=findViewById(R.id.button);
        trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });


    }


    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this,"no es pot obrir la camara",Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final ImageView imageView = findViewById(R.id.imageView);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            imageView.setImageBitmap(imageBitmap);
            //guardar a un fitxer


            File path =getApplicationContext().getFilesDir();
            File foto= new File(path,"foto.jpg");

            try {
                FileOutputStream os = new FileOutputStream(foto);
                imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,os);
            }catch (FileNotFoundException fnfe){
                fnfe.printStackTrace();
            }
        }
    }

//getexternalfilesdir



}