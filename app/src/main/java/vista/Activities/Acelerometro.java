package vista.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostzone.R;

public class Acelerometro extends AppCompatActivity implements SensorEventListener {

    SensorManager manager;
    Sensor sensor;
    TextView x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);
        Obtener();
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //acelerometro
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



    }

    private void Obtener(){

        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Estoy en el onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Estoy en el onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "Estoy en el onResume", Toast.LENGTH_SHORT).show();
        manager.registerListener(this, sensor, manager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(this, "Estoy en el onPause", Toast.LENGTH_SHORT).show();
        manager.unregisterListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Estoy en el onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Estoy en el onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {

        float a, b, c;
        a = event.values[0];
        b = event.values[1];
        c = event.values[2];

        x.setText(String.valueOf(a));
        y.setText(String.valueOf(b));
        z.setText(String.valueOf(c));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {



    }
}
