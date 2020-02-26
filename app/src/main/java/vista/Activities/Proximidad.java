package vista.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lostzone.R;

public class Proximidad extends AppCompatActivity implements SensorEventListener {

    TextView txt;
    SensorManager manager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximidad);
        Obtener();
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //proximitrometro xd
        sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    private void Obtener(){
        txt = findViewById(R.id.aproximacion);
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
        //txt.setText(String.valueOf(x));
        int color = R.color.colorAccent;
        if (x <= 0){
            txt.setBackgroundResource(color);
        } else {
            txt.setBackgroundResource(R.color.colorPrimaryDark);
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
