package com.example.weather_app;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e;
    Button b;
    TextView t;
    ImageView i;

    // AsyncTask to fetch stock info
    class getStockInfo extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... urls) {
            StringBuilder result1 = new StringBuilder();
            StringBuilder result2 = new StringBuilder();
            String[] results = new String[2];  // To store both results

            // First API call for stock price
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    result1.append(line).append("\n");
                }
                results[0] = result1.toString();  // Stock price info
            } catch (Exception e) {
                e.printStackTrace();
                results[0] = null;
            }

            // Second API call for company name
            try {
                URL url = new URL(urls[1]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    result2.append(line).append("\n");
                }
                results[1] = result2.toString();  // Company name info
            } catch (Exception e) {
                e.printStackTrace();
                results[1] = null;
            }

            return results;  // Returning both stock price and company name info
        }

        @Override
        protected void onPostExecute(String[] result) {
            super.onPostExecute(result);
            try {
                if (result[0] != null) {
                    // Parsing stock price from the first API (Alpha Vantage)
                    JSONObject jsonObject = new JSONObject(result[0]);
                    JSONObject globalQuote = jsonObject.getJSONObject("Global Quote");
                    String stockPriceValue = globalQuote.getString("05. price");
                    String percentageChangeValue = globalQuote.getString("10. change percent");

                    // Parsing company name from the second API (Finnhub)
                    if (result[1] != null) {
                        JSONObject jsonObjectCompany = new JSONObject(result[1]);
                        String companyNameValue = jsonObjectCompany.getString("name");

                        // Set the text with stock info
                        t.setText("Company: " + companyNameValue + "\nStock Price: $" + stockPriceValue + "\nChange: " + percentageChangeValue + "%");
                    } else {
                        t.setText("Company name not found.");
                    }
                } else {
                    t.setText("Stock data not available.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        e = findViewById(R.id.cityname);  // The EditText where users enter stock symbol
        b = findViewById(R.id.search);    // The search button
        t = findViewById(R.id.textView2); // The TextView for displaying stock info

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Fetching Stock Info...", Toast.LENGTH_SHORT).show();
                String stock_symbol = e.getText().toString().trim();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(e.getWindowToken(), 0);
                i=findViewById(R.id.imageView);
                i.setAlpha(200);   //0-255 transparency --0 as fullly transaprent
                if (!stock_symbol.isEmpty()) {
                    String[] urls = new String[2];
                    // Alpha Vantage API for stock price
                    urls[0] = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + stock_symbol + "&apikey=0SZMIXGGKLNZX4O9";
                    // Finnhub API for company profile
                    urls[1] = "https://finnhub.io/api/v1/stock/profile2?symbol=" + stock_symbol + "&token=crvs1e1r01qkji45ou4gcrvs1e1r01qkji45ou50";

                    // Executing the AsyncTask
                    new getStockInfo().execute(urls);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a stock symbol", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

