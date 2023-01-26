package de.cronn.cryptobookapp.activities.dashboard;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import de.cronn.cryptobookapp.R;
import de.cronn.cryptobookapp.databinding.ActivityDashboardBinding;
import de.cronn.cryptobookapp.db.DatabaseFacade;

public class DashboardActivity extends AppCompatActivity implements SensorEventListener {
    private TextView tempTextView;;
    private TextView usernameTextView;;
    private SensorManager sensorManager;
    private Sensor tmpSensor;
    private boolean isTmpSensorAvailable;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDashboardBinding binding;

    DatabaseFacade databaseFacade = new DatabaseFacade();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tempTextView = findViewById(R.id.textView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            isTmpSensorAvailable = true;
            tmpSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        } else {
            isTmpSensorAvailable = false;
            tempTextView.setText("No sensor found");
        }

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarDashboard2.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_wallet, R.id.nav_transaction_history, R.id.nav_social)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboard);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard, menu);

        usernameTextView = findViewById(R.id.username1);
        usernameTextView.setText(databaseFacade.findByUserId(getIntent().getExtras().getLong("USER-ID" )).getUser().getName());

        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboard);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        tempTextView = findViewById(R.id.textView);
        tempTextView.setText("Temperature: " + event.values[0] + "°C");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isTmpSensorAvailable) {
            sensorManager.registerListener(this, tmpSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isTmpSensorAvailable) {
            sensorManager.unregisterListener(this);
        }

    }
}