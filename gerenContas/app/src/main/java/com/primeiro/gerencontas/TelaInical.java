package com.primeiro.gerencontas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * created by David nilo
 * versão 1
 * 02/01/2020**/
public class TelaInical extends AppCompatActivity {
//Classe que controla a tela inicial.

    //DECLARANDO UM OBJETO LISTVIEW
    ListView ListMenu;

    @Override
    //MÉTODO onCreate EXECUTADO NA INICIALIZAÇÃO DA ACTIVITY
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inical);//DETERMINA O CONTEÚDO DA NOSSA ACTIVITY

        /*CARREGA O MÉTODO DE CRIAÇÃO DOS COMPONENTES*/
        this.CriarComponentes();

        /*CARREGA AS OPÇÕES DA LISTA*/
        this.CarregaOpcoesLista();

        /*CRIA EVENTOS DA LISTA*/
        this.CriarEventos();

    }
//***************************************************************************************************
    //VINCULA O COMPONENTE DA NOSSA TELA AO OBJETO DA NOSSA ATIVIDADE
    protected void CriarComponentes(){

        //VINCULANDO A LISTA DA TELA AO LISTVIEW QUE DECLARAMOS
        ListMenu = (ListView) this.findViewById(R.id.ListMenu);
    }
//**************************************************************************************************
//CRIA A OPÇÕES DA NOSSA LISTA E ADICIONA AO LISTVIEW DA NOSSA TELA.
    protected  void CarregaOpcoesLista(){

        String[] itens = new String[2];

        itens[0] = "ADICIONAR";
        itens[1] = "CONSULTAR";

        ArrayAdapter<String> arrayItens = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itens);

        ListMenu.setAdapter(arrayItens);

    }
//**************************************************************************************************
    //CRIA EVENTO PARA A LISTA
    protected void CriarEventos(){
        ListMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opcMenu = ((TextView) view).getText().toString();

                RedirecionaTela(opcMenu);
            }
        });
    }
//*********************************************************************************************
    //REDIRECIONA PARA A TELA SELECIONADA NO MENU
    protected void RedirecionaTela(String opcaoMenu){

        Intent intentRedirecionar;

        if(opcaoMenu.equals("ADICIONAR")){

            intentRedirecionar = new Intent(this, CadatroConta.class);
            startActivity(intentRedirecionar);
            finish();
        }else if (opcaoMenu.equals("CONSULTAR")){
            intentRedirecionar = new Intent(this, Consultar.class);
            startActivity(intentRedirecionar);
            finish();

        } else
            Toast.makeText(getApplicationContext(), "Opção inválida!", Toast.LENGTH_SHORT).show();

    }
}
