package com.example.chocolator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderCho_Page extends AppCompatActivity {

    int obase, oflavor;
    double kg,price;
    Button btn, order;
    Spinner sbase, sflavor;
    EditText et;
    TextView tv;
    String baseSelected, flavorSelected;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cho__page);
        getSupportActionBar().hide();

        btn = findViewById(R.id.priceBtn);
        sbase = findViewById(R.id.spinnerBase);
        sflavor = findViewById(R.id.spinnerFlavor);
        et = findViewById(R.id.quantityIP);
        tv = findViewById(R.id.priceView);
        order=findViewById(R.id.orderBtn);

        sbase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedb = sbase.getItemAtPosition(i).toString();
                switch (selectedb){
                    case "Dark Chocolate": obase=900; baseSelected= "Dark Chocolate"; break;
                    case "Milk Chocolate": obase=1000; baseSelected= "Milk Chocolate"; break;
                    case "White Chocolate": obase=1000; baseSelected= "White Chocolate"; break;
                    case "Plain Chocolate": obase=800; baseSelected= "Plain Chocolate"; break;
                    case "Marble Chocolate": obase=1100; baseSelected= "Marble Chocolate"; break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(OrderCho_Page.this,"CHOOSE BASE",Toast.LENGTH_SHORT).show();
            }
        });

        sflavor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedf= sflavor.getItemAtPosition(i).toString();
                switch (selectedf){
                    case "Cocoa": oflavor=0; flavorSelected="Cocoa"; break;
                    case "Assorted": oflavor=50; flavorSelected="Assorted"; break;
                    case "Dry Fruit": oflavor=100; flavorSelected="Dry Fruit"; break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(OrderCho_Page.this,"CHOOSE FLAVOR",Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                kg=(Double.parseDouble(et.getText().toString())/1000);
                BigDecimal bd = new BigDecimal(kg).setScale(2, RoundingMode.HALF_UP);
                double kgnew= bd.doubleValue();
                price=kgnew*(obase+oflavor);
                String priceStr= String.valueOf(price);
                tv.setText("Rs. "+priceStr+"/-");
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("Order Chocolate");
                reference.setValue("Base: "+baseSelected+", Flavor: "+flavorSelected+", Price: "+price);

                Toast.makeText(OrderCho_Page.this, "Order placed! We'll reach you shortly.", Toast.LENGTH_SHORT).show();

            }
        });

    }
}