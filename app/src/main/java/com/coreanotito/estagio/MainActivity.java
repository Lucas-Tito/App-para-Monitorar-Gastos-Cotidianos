package com.coreanotito.estagio;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button botaoChamaInput, botaoChamaUpdate, BotaoChamaDelete;
    private TableLayout tableLayout;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        atualizaTabela();




        DB= new DBHelper(this);

        botaoChamaInput=findViewById(R.id.bt_chamaInput);

        botaoChamaInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TelaInserir=new Intent(getApplicationContext(),Input.class);
                startActivity(TelaInserir);
            }
        });



            





    }

    public void atualizaTabela(){
        tableLayout=(TableLayout)findViewById(R.id.tabela);
        DB= new DBHelper(this);
        Cursor cursor = DB.getdespesa();
        if (cursor.getCount() ==0) {
            Toast.makeText(MainActivity.this,"Nenhum Dado Encontrado",Toast.LENGTH_SHORT).show();
            return;

        }else {
            cursor.moveToFirst();
            do {
                View tableRow = LayoutInflater.from(this).inflate(R.layout.activity_main,null,false);
                TextView mes  = (TextView) tableRow.findViewById(R.id.showMeses);
                TextView receita   = (TextView) tableRow.findViewById(R.id.showReceita);
                TextView despesa = (TextView) tableRow.findViewById(R.id.showDespesa);



                mes.setText(cursor.getString(1));
                receita.setText(cursor.getString(2));
                despesa.setText(cursor.getString(3));
                tableLayout.addView(tableRow);

            } while (cursor.moveToNext());
            cursor.close();
        }


    }

}