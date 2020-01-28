package com.primeiro.gerencontas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
 * 03/01/2020**/

// CLASSE DE CADASTRO DE CONTAS
public class CadatroConta extends AppCompatActivity {

    /*COMPONENTES DA TELA*/
    EditText editValor, editConta;
    Spinner spinnerMes;
    CheckBox checkBoxConta;
    Button btnVoltar, btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadatro_conta);

        //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
        this.CriarComponentes();

        //CARREGA AS OPÇÕES DO MES
        this.CarregarMes();

        //CRIA OS EVENTOS DOS COMPONENTES
        this.CriarEventos();
    }
//************************************************************************************************
//VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
    protected void CriarComponentes(){
        editConta = (EditText) this.findViewById(R.id.editConta);
        editValor = (EditText) this.findViewById(R.id.editValor);
        spinnerMes = (Spinner) this.findViewById(R.id.spinnerMes);
        checkBoxConta = (CheckBox) this.findViewById(R.id.checkBoxConta);
        btnSalvar = (Button) this.findViewById(R.id.btnSalvar);
        btnVoltar = (Button) this.findViewById(R.id.btnVoltar);

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
//*************************************************************************************************
    protected void CriarEventos(){
        //CRIANDO EVENTO NO BOTÃO SALVAR
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salvar_onClick();
            }
        });
//*************************************************************************************************
        //CRIANDO EVENTO NO BOTÃO VOLTAR
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Inicio = new Intent(getApplicationContext(), TelaInical.class);
                startActivity(Inicio);
                finish();
            }
        });
    }
//*************************************************************************************************
    //VALIDA OS CAMPOS E SALVA AS INFORMAÇÕES NO BANCO DE DADOS
    protected  void Salvar_onClick(){

        if (editConta.getText().toString().trim().equals("")){
            Mensagens.Alert(this, this.getString(R.string.conta_obrigatorio));
            editConta.requestFocus();
        } else  if(editValor.getText().toString().trim().equals("")){
            Mensagens.Alert(this, this.getString(R.string.valor_obrigatorio));
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

            /*SALVANDO UM NOVO REGISTRO*/
            new dbControle(this).Salvar(conta);

            /*MENSAGEM DE SUCESSO!*/
            Mensagens.Alert(this, this.getString(R.string.conta_salva_sucesso));

            LimparCampos();
        }
    }
//**************************************************************************************************
    //LIMPA OS CAMPOS APÓS SALVAR AS INFORMAÇÕES
    protected void LimparCampos(){

        editConta.setText(null);
        editValor.setText(null);
        checkBoxConta.setChecked(false);
    }
}
