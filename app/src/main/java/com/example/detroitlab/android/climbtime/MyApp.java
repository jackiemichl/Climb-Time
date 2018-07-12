package com.example.detroitlab.android.climbtime;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyApp extends Application {

    Map<String, Session> store = new HashMap<>();
    private SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        getStore();
        JodaTimeAndroid.init(this);
        DateTime now = new DateTime();
        String key = String.valueOf(now.getMillis());

        ArrayList<Climb> climbs = new ArrayList<>();

        Session session = new Session(now, climbs);
        store.put(key, session);
        Toast.makeText(this, key, Toast.LENGTH_LONG).show();
        store.remove(key, session);
        Toast.makeText(this, key + " removed", Toast.LENGTH_LONG).show();

    }

    public void saveStore() {
        SharedPreferences.Editor editor = preferences.edit();
        String storeJson = new Gson().toJson(store);
        editor.putString("STORE", storeJson);
        editor.apply();
    }

    public void getStore() {
        String storeJson = preferences.getString("STORE", "{}");
        Toast.makeText(this, "Store!", Toast.LENGTH_LONG).show();
    }
}
