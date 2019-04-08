package upec.projetandroid20182019;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Carte c;
    SensorManager sensorManager;
    Sensor sensorAccelerometre;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = findViewById(R.id.carte);

        sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);

        sensorAccelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        registerL();



    }


    public void registerL(){
        sensorManager.registerListener(this , sensorAccelerometre ,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterL(){
        sensorManager.unregisterListener(this,sensorAccelerometre);

    }

//Methode from SensorEventListener
    @Override
    public void onSensorChanged(SensorEvent event) {
        float changeValX = event.values[0];
        float changeValY = event.values[1];


        c.setConstanteAccelBouleX(changeValX);
        c.setConstanteAccelBouleY(changeValY);

        c.updateBoule();


    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override protected  void onResume(){
        super.onResume();
        registerL();
    }

    @Override protected  void onPause(){
        super.onPause();
        unregisterL();
    }



    public void win(){
            Intent i = new Intent(this, ActivityWin.class);
            startActivity(i);
    }


}


//REFLECHIS BIEN : OU EST CE QU'ON VERIFIE LES COORDON : DANS LE MAIN  OU  DANS LA CARTE