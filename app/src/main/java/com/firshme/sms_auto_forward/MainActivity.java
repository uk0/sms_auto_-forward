package com.firshme.sms_auto_forward;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.navigation.ui.AppBarConfiguration;


import com.firshme.sms_auto_forward.databinding.ActivityMainBinding;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private EditText dingdingToken;
    private EditText phone;
    private Button savebtn_1;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String cnf  = loadFile();
        Intent service = new Intent(this, MyService.class);
        this.startService(service);
        dingdingToken = (EditText) findViewById(R.id.editTextTextPersonName2);
        phone = (EditText) findViewById(R.id.editTextPhone);
        phone.setText(cnf.split(",")[0]);
        dingdingToken.setText(cnf.split(",")[1]);
        savebtn_1 = (Button) findViewById(R.id.savebtn_1);
        savebtn_1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), phone.getText() + "," + dingdingToken.getText(), duration);
                saveFile();
                toast.show();
            }
        });

    }


    String loadFile(){
        try {
            FileInputStream inStream = openFileInput("config.dat");
            byte data[]=new byte[inStream.available()];
            inStream.read(data);
            inStream.close();
            String str=new String(data);
           return str;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    void saveFile(){

         try{
            FileOutputStream outStream = this.openFileOutput("config.dat", MODE_PRIVATE);
            String configLine = phone.getText().toString() + "," + dingdingToken.getText().toString();
            outStream.write(configLine.getBytes());
            outStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}