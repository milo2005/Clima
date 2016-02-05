package com.example.estacionvl_tc_014.clima;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.estacionvl_tc_014.clima.net.HttpAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

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

        loadClima();
    }

    public void loadClima(){
        HttpAsyncTask task =  new HttpAsyncTask(HttpAsyncTask.METHOD_GET, this);
        task.execute(getString(R.string.url));
        progress.show();
    }

    @Override
    public void onResponseReceived(String response) {
        progress.dismiss();

        try {
            JSONObject obj = new JSONObject(response);
            JSONObject query = obj.getJSONObject("query");
            JSONObject results = query.getJSONObject("results");
            JSONObject channel = results.getJSONObject("channel");
            JSONObject atmosphere = channel.getJSONObject("atmosphere");

            String h = atmosphere.getString("humidity");
            String p = atmosphere.getString("pressure");

            JSONObject item = channel.getJSONObject("item");
            JSONObject condition  = item.getJSONObject("condition");

            String d = condition.getString("text");
            String t = condition.getString("temp");


            temperatura.setText(t);
            humedad.setText(h);
            presion.setText(p);
            descripcion.setText(d);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
