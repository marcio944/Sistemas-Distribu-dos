package com.aplicativo.lenovouser.operao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {

    String ip;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();
        ip = bundle.getString("ip").toString();
    }


    public void adicao(View view) {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                Socket socket = null;
                DataOutputStream dataOutputStream = null;
                DataInputStream dataInputStream = null;
                editText1 = (EditText) findViewById(R.id.editText_Valor1);
                editText2 = (EditText) findViewById(R.id.editText_Valor2);
                float v1 = Float.parseFloat(editText1.getText().toString());
                float v2 = Float.parseFloat(editText2.getText().toString());
                String str = "adicao";

                try {
                    socket = new Socket(ip, 10000);
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataInputStream = new DataInputStream(socket.getInputStream());
                    dataOutputStream.writeFloat(v1);
                    dataOutputStream.writeFloat(v2);
                    dataOutputStream.writeUTF(str);
                    final float res = dataInputStream.readFloat();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Resultado: " + res, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (socket != null) {
                            socket.close();
                        }
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        /*if (dataInputStream != null) {
                            dataInputStream.close();
                        }*/
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        thread.start();

    }

    public void multiplicacao(View view) {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                Socket socket = null;
                DataOutputStream dataOutputStream = null;
                DataInputStream dataInputStream = null;
                editText1 = (EditText) findViewById(R.id.editText_Valor1);
                editText2 = (EditText) findViewById(R.id.editText_Valor2);
                float v1 = Float.parseFloat(editText1.getText().toString());
                float v2 = Float.parseFloat(editText2.getText().toString());
                String str = "multiplicacao";

                try {
                    socket = new Socket(ip, 10000);
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataInputStream = new DataInputStream(socket.getInputStream());
                    dataOutputStream.writeFloat(v1);
                    dataOutputStream.writeFloat(v2);
                    dataOutputStream.writeUTF(str);
                    final float res = dataInputStream.readFloat();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Resultado: " + res, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (socket != null) {
                            socket.close();
                        }
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        thread.start();

    }

}
