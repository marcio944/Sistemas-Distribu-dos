package com.aplicativo.lenovouser.androidsocket;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    String ip;
    float valor;
    ImageView imageView;
    float valorLido;
    private HandlerThread handlerThread;
    private Handler handler;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();
        ip = bundle.getString("ip").toString();
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        handler = new Handler();
        //startRepeatingTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                iniciar();
            }finally {
                handler.postDelayed(mStatusChecker, 3000);
            }
        }
    };

    public void startRepeatingTask(View view){
        mStatusChecker.run();
    }

    public void stopRepeatingTask(View view){
        handler.removeCallbacks(mStatusChecker);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            valor = sensorEvent.values[0];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void iniciar() {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                Socket socket = null;
                DataOutputStream dataOutputStream = null;
                DataInputStream dataInputStream = null;

                try {
                    socket = new Socket(ip, 10000);
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    //dataInputStream = new DataInputStream(socket.getInputStream());
                    dataOutputStream.writeFloat(valor);
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

}
