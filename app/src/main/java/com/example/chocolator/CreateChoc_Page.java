package com.example.chocolator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateChoc_Page extends AppCompatActivity {

    int cbase, csize, ctopping, price;
    Button btn, order;
    Spinner csbase, cstopping, cssize;
    TextView tv;
    String baseSelected, toppingSelected, sizeSelected;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_choc__page);
        getSupportActionBar().hide();

        btn = findViewById(R.id.priceBtn);
        tv = findViewById(R.id.priceView);
        csbase = findViewById(R.id.spinnerBase);
        cstopping = findViewById(R.id.spinnerTopping);
        cssize = findViewById(R.id.spinnerSize);
        order = findViewById(R.id.orderBtn);

        csbase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedb = csbase.getItemAtPosition(i).toString();
                switch (selectedb){
                    case "Dark Chocolate": cbase=900; baseSelected= "Dark Chocolate"; break;
                    case "Milk Chocolate": cbase=1000; baseSelected= "Milk Chocolate"; break;
                    case "White Chocolate": cbase=1000; baseSelected= "White Chocolate"; break;
                    case "Plain Chocolate": cbase=800; baseSelected= "Plain Chocolate"; break;
                    case "Marble Chocolate": cbase=1100; baseSelected= "Marble Chocolate"; break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cssize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selecteds= cssize.getItemAtPosition(i).toString();
                switch (selecteds){
                    case "3 X 5": csize=100; sizeSelected="small"; break;
                    case "4 X 6": csize=120; sizeSelected="big"; break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cstopping.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedt= cstopping.getItemAtPosition(i).toString();
                switch (selectedt){
                    case "Chocochips": ctopping=25; toppingSelected="Chocochips"; break;
                    case "Sprinkles": ctopping=15; toppingSelected="Sprinkles"; break;
                    case "Silver Balls": ctopping=20; toppingSelected="Silver Balls"; break;
                    case "Fruit and Nut": ctopping=35; toppingSelected="Fruit and Nut"; break;
                    case "Crackle Pops": ctopping=10; toppingSelected="Crackle Pops"; break;
                    case "None": ctopping=0; toppingSelected="None"; break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price=((csize*cbase)/1000)+ctopping;
                String total= String.valueOf(price);
                tv.setText("Rs. "+total+"/-");
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode= FirebaseDatabase.getInstance();
                reference=rootNode.getReference("Create Chocolate Bar");
                reference.setValue("Size: "+sizeSelected+", Base: "+baseSelected+", Topping: "+toppingSelected);

                Toast.makeText(CreateChoc_Page.this, "Order placed! We'll reach you shortly.", Toast.LENGTH_SHORT).show();

            }
        });
    }


}