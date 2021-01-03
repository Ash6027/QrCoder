package com.example.android.qrcoder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    Button generatebtn,scanbtn,Barcode;
    EditText qrvalue;
    ImageView qrImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrvalue=findViewById(R.id.editText);
        qrImage=findViewById(R.id.imageView);
        generatebtn=findViewById(R.id.button2);
        scanbtn=findViewById(R.id.button);
        Barcode=findViewById(R.id.button5);



            generatebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String data = qrvalue.getText().toString();
                    if (data != null) {

                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                        try {
                            BitMatrix bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.QR_CODE, 150, 150);
                            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                            qrImage.setImageBitmap(bitmap);
                            qrImage.setVisibility(View.VISIBLE);

                        } catch (WriterException e) {
                            e.printStackTrace();
                        }


                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Field Required",Toast.LENGTH_SHORT).show();
                    }
                }
            });



        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Scanner.class));
            }
        });
        Barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Barcoder.class);
                startActivity(i);
            }
        });
      }

    }
