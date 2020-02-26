package vista.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lostzone.R;

public class Luz extends AppCompatActivity implements SensorEventListener {

    TextView txt;
    SensorManager manager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luz);
        Obtener();
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //proximitrometro xd
        sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    private void Obtener(){
        txt = findViewById(R.id.luz);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, sensor, manager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        txt.setText(String.valueOf(x));
        int color = R.color.colorAccent;
        if (x <= 15){
            txt.setBackgroundResource(R.color.colorPrimaryDark);
        } else if (x <= 50 && x > 15 ){
            txt.setBackgroundResource(color);
        } else if (x <= 100 && x > 50){
            txt.setBackgroundResource(R.color.colorPrimary);
        } else {
            txt.setBackgroundColor(Color.parseColor("#fff000"));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
