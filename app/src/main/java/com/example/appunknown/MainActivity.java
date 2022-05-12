package com.example.appunknown;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etPhone;
    private ImageButton btnCall, btnCamera;

    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

        btnCall.setOnClickListener(view ->{
                activateCall();
        });
    }

    private void activateCall() {
        phoneNumber=etPhone.getText().toString();
        if(!phoneNumber.isEmpty()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            }else{
                oldestVersionconfg();
            }
        }
    }

    private void oldestVersionconfg() {
        //intent implicit para que un componente realice una accion, el sistema busca al mejor para hacerlo
        Intent intentCall= new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:"+phoneNumber));
        if(reviewPermitions(Manifest.permission.CALL_PHONE)){
            startActivity(intentCall);
        }
        else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }

    }

    private void initializeViews() {
        etPhone=findViewById(R.id.etPhone);
        btnCall=findViewById(R.id.btnCall);
        btnCamera=findViewById(R.id.btnCamera);
    }

    private boolean reviewPermitions(String permit) {
        int permitValue=this.checkCallingOrSelfPermission(permit);
        return permitValue== PackageManager.PERMISSION_GRANTED;
    }
}