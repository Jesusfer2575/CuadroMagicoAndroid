package com.example.adrian.cuadromagico;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button bttn1,bttn2;
    private EditText ett;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bttn1 = (Button) findViewById(R.id.xBttn1);
        ett = (EditText) findViewById(R.id.xNumero);
        txt = (TextView) findViewById(R.id.xTablaResultado);
        bttn2 = (Button) findViewById(R.id.xBttn2);

        bttn1.setOnClickListener(this);
        bttn2.setOnClickListener(this);
    }

    public int[][] cuadroMagico(int n){
        int matriz[][] = new int[n+2][n+2];
        int contador=1;
        int c_ult=0;
        int f_ult=0;
        int c_sig=n/2;
        int f_sig=0;
        for (int i = 0; i<n; i++){
            for (int j = 0; j<n; j++){
                //guardamos el numero en la matriz
                matriz[f_sig][c_sig]=contador;

                //guardamos la ultima posicion donde guardamos algo
                f_ult=f_sig;
                c_ult=c_sig;

                //primer paso para la siguiente posicion
                f_sig=f_ult-1;
                c_sig=c_ult+1;

                if(f_sig<0){
                    f_sig=n-1;
                }
                if(c_sig>=n){
                    f_sig=f_ult-1;
                    c_sig=0;
                }

                if(f_sig==-1 || matriz[f_sig][c_sig]!=0){
                    f_sig=f_ult+1;
                    c_sig=c_ult;
                }
                contador++;
            }
        }
        return matriz;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.xBttn1){
            Integer n = Integer.parseInt(ett.getText().toString());
            int [][]Cuadro = cuadroMagico(n);
            String result = "";
            int sum = 0;
            for(int i=0;i<n;i++){
                sum = 0;
                for(int j=0;j<n;j++){
                    result+= Cuadro[i][j] + "  ";
                    sum += Cuadro[i][j];
                }
                result += "\n";
            }

            result += "\n\n";
            result += "La suma de sus lados es: " + sum;
            txt.setText(result);
        }
        else if(v.getId() == R.id.xBttn2){
            txt.setText("");
            ett.setText("");
            ett.setHint("Digite un número impar");
        }
    }
}
