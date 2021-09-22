package com.btsinfo.convertisseur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Devises :
    private Spinner spDevise1;
    private Spinner spDevise2;
    private String choixdevise1;
    private String choixdevise2;
    private EditText etDev1;
    private Button btConvert;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spDevise1 = (Spinner) findViewById(R.id.spDevise1);
        spDevise2 = (Spinner) findViewById(R.id.spDevise2);
        etDev1 = (EditText) findViewById(R.id.etDev1);
        btConvert = (Button) findViewById(R.id.btConvert);
        tvResult = (TextView) findViewById(R.id.tvResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.devise,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDevise1.setAdapter(adapter);


        spDevise1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choixdevise1 = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(adapterView.getContext(),choixdevise1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.devise,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDevise2.setAdapter(adapter2);


        spDevise2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choixdevise2 = adapterView.getItemAtPosition(i).toString();
               // Toast.makeText(adapterView.getContext(),choixdevise2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etDev1.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Vous devez rentrer un montant", Toast.LENGTH_LONG).show();
                }else {
                    String affiche = "Conversion de " + choixdevise1 + " vers " + choixdevise2 + " : \n";
                    double d = (double) Double.parseDouble(etDev1.getText().toString());
                    // Toast.makeText(getApplicationContext(), Double.toString(d), Toast.LENGTH_SHORT).show();
                    if (choixdevise1.equals(choixdevise2)) {
                        affiche += "Memes devises\n";
                    } else if (choixdevise1.equalsIgnoreCase("Euros")) {
                        affiche += "Euro : " + etDev1.getText().toString() + "\n";
                        if (choixdevise2.equalsIgnoreCase("Dollars")) {
                            affiche += "Dollars : " + (Math.round(d * 1.17 * 100.0) / 100.0) + "\n";
                        } else if (choixdevise2.equalsIgnoreCase("Livre")) {
                            affiche += "Livre : " + (Math.round(d * 0.85 * 100.0) / 100.0) + "\n";
                        }
                    } else if (choixdevise1.equalsIgnoreCase("Dollars")) {
                        affiche += "Dolars : " + etDev1.getText().toString() + "\n";
                        if (choixdevise2.equalsIgnoreCase("Euros")) {
                            affiche += "Euro : " + (Math.round(d * 0.85 * 100.0) / 100.0) + "\n";
                        } else if (choixdevise2.equalsIgnoreCase("Livre")) {
                            affiche += "Livre : " + (Math.round(d * 0.73 * 100.0) / 100.0) + "\n";
                        }
                    } else if (choixdevise1.equalsIgnoreCase("Livre")) {
                        affiche += "Livre : " + etDev1.getText().toString() + "\n";
                        if (choixdevise2.equalsIgnoreCase("Euros")) {
                            affiche += "Euro : " + (Math.round(d * 1.16 * 100.0) / 100.0) + "\n";
                        } else if (choixdevise2.equalsIgnoreCase("Dollars")) {
                            affiche += "Dollars : " + (Math.round(d * 1.36 * 100.0) / 100.0) + "\n";
                        }
                    }
                    tvResult.setText(affiche);
                }
            }
        });
    }
}