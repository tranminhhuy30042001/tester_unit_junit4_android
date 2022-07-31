package com.quanao.hanghieu.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.quanao.hanghieu.R;
import com.quanao.hanghieu.data.Capture;
import com.quanao.hanghieu.data.Utils;

public class Barcode extends AppCompatActivity {

    Button btnScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        btnScanner = findViewById(R.id.btn_scan);
        scanCode();

        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void scanCode(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(Capture.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher  = registerForActivityResult(new ScanContract(), result ->
    {
        if(result.getContents()!=null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(Barcode.this);
            builder.setTitle("Result");
            Utils.giamgia = result.getContents();
            builder.setMessage("success discount " +result.getContents() +" $");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }else{
            Toast.makeText(getApplicationContext(),"You didn't scan anything",Toast.LENGTH_SHORT).show();
        }
    });
}