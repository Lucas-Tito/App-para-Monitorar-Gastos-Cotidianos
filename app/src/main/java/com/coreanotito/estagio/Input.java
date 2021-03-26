package com.coreanotito.estagio;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Input extends AppCompatActivity {

    Button voltarMain, executar;
    EditText Receita, Despesa;
    private TextInputLayout dropdown_layout;
    private AutoCompleteTextView insertMeses;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        voltarMain=findViewById(R.id.bt_voltarMain);
        executar=findViewById(R.id.execInserir);

        insertMeses = findViewById(R.id.insertMeses);
        dropdown_layout = findViewById(R.id.dropdown_layout);
        Receita=findViewById(R.id.insertReceita);
        Despesa=findViewById(R.id.insertDespesa);
        DB= new DBHelper(this);

                    //Converte o Spinner em array
          String[] Meses = getResources().getStringArray(R.array.meses);
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.dropdown_item, Meses);
          adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        insertMeses.setAdapter(adapter);

                    //Código para o botão voltar
        voltarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

                    //Código para o botão executar
    executar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String mesTXT = insertMeses.toString();

            String pegarIntDespesa =Despesa.getText().toString();
            Integer despesaINT = Integer.parseInt(pegarIntDespesa);

            String pegarIntReceita =Receita.getText().toString();
            Integer receitaINT = Integer.parseInt(pegarIntReceita);


            Boolean checkinsertdata = DB.insertdespesa(mesTXT, despesaINT, receitaINT);
            if (checkinsertdata==true)
                Toast.makeText(Input.this,"Sucesso",Toast.LENGTH_SHORT).show();
            else Toast.makeText(Input.this, "Falha", Toast.LENGTH_SHORT).show();


        }
    });





    }
}