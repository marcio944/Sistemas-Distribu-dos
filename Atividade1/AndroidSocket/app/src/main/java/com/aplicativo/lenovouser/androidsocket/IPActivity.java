package com.aplicativo.lenovouser.androidsocket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IPActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
    }

    public void enviar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        editText = (EditText) findViewById(R.id.editText_IP);
        intent.putExtra("ip", editText.getText().toString());
        startActivity(intent);
    }

}
