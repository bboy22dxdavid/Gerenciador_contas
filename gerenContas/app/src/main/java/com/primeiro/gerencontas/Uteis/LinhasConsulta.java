package com.primeiro.gerencontas.Uteis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.primeiro.gerencontas.BancoDados.dbControle;
import com.primeiro.gerencontas.Consultar;
import com.primeiro.gerencontas.EditarConta;
import com.primeiro.gerencontas.ObjConta.Conta;
import com.primeiro.gerencontas.R;

import java.util.ArrayList;
import java.util.List;

/**
 * created by David nilo
 * versão 1
 * 04/01/2020**/
//CLASSE QUE CONTROLA AS LINHAS ONDE SERA VISUALIZADO AS CONTAS
public class LinhasConsulta extends BaseAdapter {

    //CRIANDO UM OBJETO LayoutInflater PARA FAZER LINK A NOSSA VIEW(linha_consulta.xml)
    private static LayoutInflater layoutInflater = null;

    //CRIANDO UMA LISTA DE CONTAS
    List<Conta> contas = new ArrayList<Conta>();

    //CIRANDO UM OBJETO DA NOSSA CLASSE QUE FAZ ACESSO AO BANCO DE DADOS
    dbControle bancodados;

    //CRIANDO UM OBJETO DA NOSSA ATIVIDADE QUE CONTEM A LISTA
    private Consultar consultar;

    /*CONSTRUTOR QUE VAI RECEBER A NOSSA ATIVIDADE COMO PARAMETRO E A LISTA DE CONTAS
     QUE VAI RETORNAR, DA NOSSA BASE DE DADOS
     */
//*************************************************************************************************
    public  LinhasConsulta (Consultar consultar, List<Conta> contas){
        this.contas = contas;
        this.consultar = consultar;
        this.layoutInflater = (LayoutInflater) this.consultar.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bancodados = new dbControle(consultar);
    }
//*************************************************************************************************
    //RETORNA A QUANTIDADE DE REGISTROS DA LISTA
    @Override
    public int getCount() {
        return contas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
//*************************************************************************************************
    //ESSE MÉTODO SETA OS VALORES DE UM ITEM DA NOSSA LISTA DE PESSOAS PARA UMA LINHA DO NOSSO LISVIEW
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //CRIANDO UM OBJETO DO TIPO View PARA ACESSAR O NOSSO ARQUIVO DE LAYOUT linha_consultar.xml
        final View viewLinhas = layoutInflater.inflate(R.layout.linhas_consulta, null);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(linha_consultar.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA CONTA
        TextView codigo = (TextView)viewLinhas.findViewById(R.id.Codigo);
        //CAMPO QUE VAI MOSTRAR O NOME DA CONTA
        TextView nome = (TextView)viewLinhas.findViewById(R.id.nome);
        //CAMPO QUE VAI MOSTRAR O VALOR
        TextView valor = (TextView)viewLinhas.findViewById(R.id.valor);
        //CAMPO QUE VAI MOSTRAR O MES
        TextView mes = (TextView)viewLinhas.findViewById(R.id.mes);
        //CAMPO QUE VAI MOSTRAR O REGISTRO
        TextView registro = (TextView)viewLinhas.findViewById(R.id.RegistroAtivo);
        //CRIANDO O BOTÃO  EXCLUIR PARA DELETARMOS UM REGISTRO DO BANCO DE DADOS
        Button buttonExcluir = (Button)viewLinhas.findViewById(R.id.buttonExcluir);
        //CRIANDO O BOTÃO  EDITAR UM REGISTRO DO BANCO DE DADOS
        Button buttonEditar = (Button)viewLinhas.findViewById(R.id.buttonEditar);

        //SETANDO OS CAMPO DA NOSSA VIEW
        mes.setText(this.GetMes(contas.get(position).getMes()));
        codigo.setText(String.valueOf(contas.get(position).getCodigo()));
        nome.setText(contas.get(position).getNome());
        valor.setText(contas.get(position).getValor());

        if(contas.get(position).getRegistroAtivo() == 1) {
            registro.setText("Registro Ativo:Não");
        }else {
            registro.setText("Registro Ativo:Sim");
        }
//*************************************************************************************************
        //CRIANDO EVENTO CLICK PARA O BOTÃO DE EXCLUIR REGISTRO
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EXCLUINDO UM REGISTRO
                bancodados.Excluir(contas.get(position).getCodigo());
                //MOSTRA A MENSAGEM APÓS EXCLUIR UM REGISTRO
                Toast.makeText(consultar,"Registro excluido com sucesso!", Toast.LENGTH_LONG).show();
                //CHAMA O MÉTODO QUE ATUALIZA A LISTA COM OS REGISTROS QUE AINDA ESTÃO NA BASE
                AtualizarLista();
            }
        });
//***************************************************************************************************
//CRIANDO EVENTO CLICK PARA O BOTÃO QUE VAI REDIRECIONAR PARA A TELA DE EDIÇÃO
        // DO REGISTRO.
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRedirecionar = new Intent(consultar, EditarConta.class);

                intentRedirecionar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intentRedirecionar.putExtra("id_pessoa",contas.get(position).getCodigo());

                consultar.startActivity(intentRedirecionar);

                consultar.finish();
            }
        });
        return viewLinhas;
    }
//*************************************************************************************************
//MÉTODO QUE RETORNA A DESCRIÇÃO DO MES POR CÓDIGO
    public String GetMes(String codigoMes){

        if(codigoMes.equals("1")) {
            return "JANEIRO";
        }else if(codigoMes.equals("2")) {
            return "FEVEREIRO";
        }else if(codigoMes.equals("3")) {
            return "MARÇO";
        }else if(codigoMes.equals("4")) {
            return "ABRIL";
        } else if(codigoMes.equals("5")) {
            return "MAIO";
        }else if(codigoMes.equals("6")) {
            return "JUNHO";
        }else if(codigoMes.equals("7")) {
            return "JULHO";
        }else if(codigoMes.equals("8")) {
            return "AGOSTO";
        }else if(codigoMes.equals("9")) {
            return "SETEMBRO";
        }else if(codigoMes.equals("10")) {
            return "OUTUBRO";
        }else if(codigoMes.equals("11")) {
            return "NOVEMBRO";
        }else {
            return "DEZEMBRO";
        }

    }
//************************************************************************************************
//ATUALIZA A LISTTA DEPOIS DE EXCLUIR UM REGISTRO
    public void AtualizarLista(){

        this.contas.clear();
        this.contas = bancodados.SelecionarTodas();
        this.notifyDataSetChanged();
    }
}
