package com.zain.project5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Objects;

/**
 * @author Zain Ali, Clarissa Hwang
 */
public class Newark extends AppCompatActivity {
    private final double SALES_TAX = 0.06625;
    private final int STUDENT_PRICE = 8;
    private final int SENIOR_PRICE = 8;
    private final int ADULT_PRICE = 15;

    ImageView image;
    TextView desc;
    TextView location;

    Spinner adultSpinner;
    Spinner studentSpinner;
    Spinner seniorSpinner;

    TextView adultText;
    TextView studentText;
    TextView seniorText;

    TextView subTotal;
    TextView salesTax;
    TextView totalPrice;

    int sub;
    double sales;
    double totalNum;

    /**
     * Go back
     * @return true
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /**
     * Class on activity create
     * @param savedInstanceState previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.museum_view);
        image = findViewById(R.id.museum_image);
        desc = findViewById(R.id.museum_desc);
        location = findViewById(R.id.museum_loc);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        adultSpinner = findViewById(R.id.adult_spinner);
        studentSpinner = findViewById(R.id.student_spinner);
        seniorSpinner = findViewById(R.id.senior_spinner);

        adultText = findViewById(R.id.adult_text);
        studentText = findViewById(R.id.student_text);
        seniorText = findViewById(R.id.senior_text);

        subTotal = findViewById(R.id.sub_price);
        salesTax = findViewById(R.id.tax_price);
        totalPrice = findViewById(R.id.total_price);

        location.setText(getString(R.string.newark_loc));
        desc.setText(getString(R.string.newark_desc));
        image.setImageResource(R.drawable.newark);
        image.setOnClickListener(v -> {
            Uri uri = Uri.parse(getString(R.string.newark_site));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        adultText.append(" ($" + ADULT_PRICE + ")");
        studentText.append(" ($" + STUDENT_PRICE + ")");
        seniorText.append(" ($" + SENIOR_PRICE + ")");

        adultSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.tickets)));
        adultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updatePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updatePrice();
            }
        });
        studentSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.tickets)));
        studentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updatePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updatePrice();
            }
        });
        seniorSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.tickets)));
        seniorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updatePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updatePrice();
            }
        });

    }

    /**
     * Method called on the start of the activity
     */
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), getString(R.string.toast_message), Toast.LENGTH_SHORT).show();
    }

    /**
     * Calculate and update price
     */
    private void updatePrice() {
        sub = (Integer.parseInt((String) adultSpinner.getSelectedItem())*ADULT_PRICE)
                + (Integer.parseInt((String) studentSpinner.getSelectedItem())*STUDENT_PRICE)
                + (Integer.parseInt((String) seniorSpinner.getSelectedItem())*SENIOR_PRICE);
        String subHold = String.format(Locale.getDefault(),"$%d", sub);
        subTotal.setText(subHold);

        sales = sub * SALES_TAX;
        String taxHold = String.format(Locale.getDefault(),"$%.2f", sales);
        salesTax.setText(taxHold);

        totalNum = sub + sales;
        String totalHold = String.format(Locale.getDefault(),"$%.2f", totalNum);
        totalPrice.setText(totalHold);

    }
}

