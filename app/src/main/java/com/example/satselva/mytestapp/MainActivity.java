package com.example.satselva.mytestapp;

import static com.example.satselva.mytestapp.CategoryData.CategoryEntry;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import static android.util.Base64.encode;

public class MainActivity extends AppCompatActivity {

    private static final String CATEGORY_URL = "http://188.166.188.189:3000/category/list";

    private static final String ARTICLE_URL = "http://188.166.188.189:3000/category/list";

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        this.dbHelper = new DatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void listCategory(View v) {
        Set<Category> categories = new HashSet<>();
        try {
            Log.d("CATEGORY", String.format("Making request to category list with URL [%s]", CATEGORY_URL));
            URL url = new URL(CATEGORY_URL);
            final String randomId = UUID.randomUUID().toString();
            final String networkResponse = String.format("{\"count\":1,\"category\":[{\"_id\":\"%s\",\"createdBy\":\"Admin\",\"name\":\"Burning Issues\",\"__v\":0,\"modifiedBy\":\"Ranjith\",\"iconUrl\":\"http://d27bygd3qv5fha.cloudfront.net/assets/category/icons/Burning-Issues1.jpg\",\"subCategories\":[],\"modifiedDate\":\"2016-01-17T19:19:46.712Z\",\"createdDate\":\"2016-01-11T12:39:31.683Z\"}]}", randomId);
            //final AsyncTask<String, Authenticator.RequestorType, String> networkTaskResponse = new NetworkTask(NetworkTask.RequestType.GET).execute(CATEGORY_URL);
            //Log.d("CATEGORY", String.format("Category list returned with the response %s", networkTaskResponse.get()));
            Log.d("CATEGORY", String.format("Category list returned with the response %s", networkResponse));
            JSONObject categoryJsonData = new JSONObject(networkResponse);
            JSONArray categoriesData = categoryJsonData.getJSONArray("category");
            for(int index=0; index < categoriesData.length(); index++) {
                JSONObject categoryJson = categoriesData.getJSONObject(index);
                Category category = new Category.CategoryBuilder().withId(categoryJson.getString("_id"))
                        .withName(categoryJson.getString("name")).withIconUrl(categoryJson.getString("iconUrl"))
                        .withCreatedBy(categoryJson.getString("createdBy")).withModifiedBy(categoryJson.getString("modifiedBy"))
                        .withCreatedDate(new Date()).withModifiedDate(new Date()).withV(categoryJson.getString("__v")).build();
                categories.add(category);
                dbHelper.addCategory(category);
            }
            Log.d("DEBUG", String.format("Returned %d categories from the URL [%s]", categories.size(), CATEGORY_URL));

            List<Category> categoriesResultFromDB = dbHelper.listAllCategories();
            Log.d("SQLRESULT", String.format("Found %d categories from DB", categoriesResultFromDB.size()));

            Category categoryQueryResultWithId = dbHelper.getCategory(randomId);
            Log.d("SQLRESULT", String.format("Found %s categories from DB", categoryQueryResultWithId));

        } catch(JSONException e) {
            Log.e("FATAL", String.format("Exception occurred while parsing the json data from category"), e);
        } catch(Exception e ) {
            Log.e("ERROR", String.format("Exception occurred while making request to [%s]", CATEGORY_URL), e);
        }
    }
}
