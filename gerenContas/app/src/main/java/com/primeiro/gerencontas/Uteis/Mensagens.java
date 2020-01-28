package com.primeiro.gerencontas.Uteis;

import android.app.AlertDialog;
import android.content.Context;

import com.primeiro.gerencontas.R;

/**
 * created by David nilo
 * versão 1
 * 03/01/2020**/

// CLASSE QUE GERENCIA AS MENSAGENS DE ALERTA.
public class Mensagens {

    public static void Alert(Context context, String mensagem){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        //ADICIONANDO UM TITULO A NOSSA MENSAGEM DE ALERTA
        alertDialog.setTitle(R.string.app_name);

        //MENSAGEM A SER EXIBIDA
        alertDialog.setMessage(mensagem);

        //CRIA UM BOTÃO COM O TEXTO OK SEM AÇÃO
        alertDialog.setPositiveButton("ok", null);

        //MOSTRA A MENSAGEM NA TELA
        alertDialog.show();
    }
}
