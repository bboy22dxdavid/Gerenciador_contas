package com.primeiro.gerencontas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.primeiro.gerencontas.BancoDados.DBContas;
import com.primeiro.gerencontas.BancoDados.dbControle;
import com.primeiro.gerencontas.ObjConta.Conta;
import com.primeiro.gerencontas.Uteis.Mensagens;
import com.primeiro.gerencontas.Uteis.Mes;

import java.util.ArrayList;
import java.util.List;

/**
 * created by David nilo
 * versão 1
 * 05/01/2020**/
//CLASSE QUE CONTROLA A AÇÃO DE EDITAR.
public class EditarConta extends AppCompatActivity {
    /*COMPONENTES DA TELA*/
    EditText editConta, editValor;
    Spinner spinnerMes;
    Button btnVoltar, btnAterar;
    CheckBox checkBoxConta;

    ArrayAdapter<Mes> arrayAdapterMes;
    dbControle basedados = new dbControle(this);
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_conta);

        //CHAMA O MÉTODO PARA CRIAR OS COMPONENTES DA TELA
        this.CriarComponentes();

        //CHAMA O MÉTODO QUE CRIA EVENTOS PARA OS COMPONENTES
        this.CriarEventos();

        //CARREGA AS OPÇÕES DE ESTADO CIVIL
        this.CarregarMes();

        //CARREGA OS VALORES NOS CAMPOS DA TELA.
        this.CarregaValoresCampos();
    }
//*************************************************************************************************
    //VINCULA OS COMPONENTES DA TELA(VIEW) AOS OBJETOS DECLARADOS.
    protected  void CriarComponentes(){
        editConta = (EditText)this.findViewById(R.id.editConta);
        editValor = (EditText)this.findViewById(R.id.editValor);
        spinnerMes = (Spinner)this.findViewById(R.id.spinnerMes);
        btnVoltar = (Button)this.findViewById(R.id.btnVoltar);
        btnAterar  = (Button)this.findViewById(R.id.btnAterar);
        checkBoxConta = (CheckBox) this.findViewById(R.id.checkBoxConta);
    }
//*************************************************************************************************
//MÉTODO CRIA OS EVENTOS PARA OS COMPONENTES
    protected  void CriarEventos(){
        //CRIANDO EVENTO CLICK PARA O BOTÃO ALTERAR
        btnAterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alterar_onClick();
            }
        });
//************************************************************************************************
    //CRIANDO EVENTO CLICK PARA O BOTÃO VOLTAR
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent telainicial = new Intent(getApplicationContext(), TelaInical.class);
                startActivity(telainicial);
                finish();
                }
            });
        }
//**************************************************************************************************
    //ALTERA UM REGISTRO
    protected  void Alterar_onClick(){
        //VALIDA SE OS CAMPOS ESTÃO VAZIOS ANTES DE ALTERAR O REGISTRO
        if (editConta.getText().toString().trim().equals("")){
            Mensagens.Alert(this, this.getString(R.string.conta_obrigatorio));
            editConta.requestFocus();
        }else if (editValor.getText().toString().trim().equals("")){
            Mensagens.Alert(this, this.getString(R.string.valor_obrigatorio));
            editValor.requestFocus();
        } else {
            /*CRIANDO UM OBJETO CONTA*/
            Conta conta = new Conta();

            /*SETANDO O VALOR DO CAMPO CONTA*/
            conta.setNome(editConta.getText().toString().trim());

            /*SETANDO O ENDEREÇO*/
            conta.setValor(editValor.getText().toString().trim());

            /*REALIZANDO UM CAST PARA PEGAR O OBJETO DO MES SELECIONADO*/
            Mes mes = (Mes)spinnerMes.getSelectedItem();

            /*SETANDO ESTO MES*/
            conta.setMes(mes.getCodigo());

            /*SETA O REGISTRO COMO INATIVO*/
            conta.setRegistroAtivo((byte)0);

            /*SE TIVER SELECIONADO SETA COMO ATIVO*/
            if(checkBoxConta.isChecked())
                conta.setRegistroAtivo((byte)1);

            /*ALTERANDO UM NOVO REGISTRO*/
            new dbControle(this).Atualizar(conta);

            /*MENSAGEM DE SUCESSO!*/
            AlertDialog.Builder allerta = new AlertDialog.Builder(this);

            /*MENSAGEM DE SUCESSO!*/
            Mensagens.Alert(this, this.getString(R.string.conta_salva_sucesso));
            //MENSAGEM A SER EXIBIDA
            allerta.setMessage("Registro alterado com sucesso! ");

            //CRIA UM BOTÃO COM O TEXTO OK SEM AÇÃO
            allerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //RETORNA PARA A TELA DE CONSULTA
                    Intent tela_consulta = new Intent(getApplicationContext(), Consultar.class);

                    startActivity(tela_consulta);

                    finish();
                }
            });

            //MOSTRA A MENSAGEM NA TELA
            allerta.show();
        }
    }
//************************************************************************************************
//CARREGA AS OPÇÕES DE MES PARA O COMPONENTE SPINNER
    protected void CarregarMes(){
        ArrayAdapter<Mes> arrayAdapter;
        List<Mes> itens = new ArrayList<Mes>();

        itens.add(new Mes("1", "JANEIRO"));
        itens.add(new Mes("2", "FEVEREIRO"));
        itens.add(new Mes("3", "MARÇO"));
        itens.add(new Mes("4", "ABRIL"));
        itens.add(new Mes("5", "MAIO"));
        itens.add(new Mes("6", "JUNHO"));
        itens.add(new Mes("7", "JULHO"));
        itens.add(new Mes("8", "AGOSTO"));
        itens.add(new Mes("9", "SETEMBRO"));
        itens.add(new Mes("10", "OUTUBRO"));
        itens.add(new Mes("11", "NOVEMBRO"));
        itens.add(new Mes("12", "DEZEMBRO"));

        arrayAdapter = new ArrayAdapter<Mes>(this, android.R.layout.simple_spinner_item, itens);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMes.setAdapter(arrayAdapter);
    }

    //POSICIONA O MES PARA EDIÇÃO
    protected void PosicionaMes(String chaveMes){
        int index;
        for( index = 0; index < arrayAdapterMes.getCount();index++);{

            if (((Mes) arrayAdapterMes.getItem(index)).getCodigo().equals(chaveMes)) {

                spinnerMes.setSelection(index);

            }
        }
    }
//*************************************************************************************************
//CARREGA OS VALORES NOS CAMPOS APÓS RETORNAR DO SQLITE
   protected  void CarregaValoresCampos  (){

        dbControle controle = new dbControle(this);

        //PEGA O CODIGO CONTA QUE FOI PASSADO COMO PARAMETRO ENTRE AS TELAS
        Bundle extra =this.getIntent().getExtras();
        int codigo = extra.getInt("codigo");

        //CONSULTA UMA CONTA POR CODIGO
        Conta conta = controle.Consultar(codigo);

        editConta.setText(String.valueOf(conta.getNome()));
        editValor.setText(String.valueOf(conta.getValor()));


        //POSICIONA O ESTADO CIVIL
        this.PosicionaMes(conta.getMes());

        //SETA SE O  REGISTRO ESTÁ ATIVO
        if(conta.getRegistroAtivo() == 1) {
            checkBoxConta.setChecked(true);
        }
     }
}
