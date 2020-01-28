package com.primeiro.gerencontas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * created by David nilo
 * versão 1
 * 02/01/2020**/

public class MainActivity extends AppCompatActivity implements Runnable {
//
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();//faz um redirecionamento.

        handler.postDelayed(this, 2000);
    }

    @Override
    public void run() {
        //metodo que faz a chamada do layout.
        startActivity(new Intent(this, TelaInical.class));
        finish();
    }
}
