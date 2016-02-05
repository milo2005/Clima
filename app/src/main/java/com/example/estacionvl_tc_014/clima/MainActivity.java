package com.example.estacionvl_tc_014.clima;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.estacionvl_tc_014.clima.net.HttpAsyncTask;

public class MainActivity extends AppCompatActivity implements HttpAsyncTask.HttpI {

    TextView descripcion, humedad, temperatura, presion;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descripcion = (TextView) findViewById(R.id.descripcion);
        humedad = (TextView) findViewById(R.id.hum);
        temperatura = (TextView) findViewById(R.id.temp);
        presion = (TextView) findViewById(R.id.pre);

        progress = new ProgressDialog(this);
        progress.setMessage(getString(R.string.loading));

    }

    public void loadClima(){
        HttpAsyncTask task =  new HttpAsyncTask(HttpAsyncTask.METHOD_GET, this);
        task.execute("");
        progress.show();
    }

    @Override
    public void onResponseReceived(String response) {
        progress.dismiss();
    }
}
