package com.example.rickandmortyapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.rickandmortyapp.datasources.BuscarDadosWeb;
import com.example.rickandmortyapp.datasources.Config;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    private ArrayList<HashMap<String,String>> listaDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            listaDados = new BuscarDadosWeb().execute(Config.urlApi).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String texto = listaDados.get(0).get("name");
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();

        //criando o adapter que ir configrar como os dados sao carregados
        ListAdapter adapter = new SimpleAdapter(
                this,                      //contexto que o objeto esta
                listaDados,                //local onde estao os dados
                R.layout.listview_modelo,  //item que servira de modelo para cada celula
                new String[]{"name"},   //quais campos dos dados serao carregados
                new int[]{R.id.txtNome} //objetos de tela onde dados vao ser carregados
        );

        //adicionando o adaptador criado na listView da tela
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //carregando os dados do item selecionado na lista pelo index
        HashMap<String, String> personagem =  listaDados.get(position);

        //criando o caminho para abrir a tela de detalhes
        Intent telaDetalhes = new Intent(MainActivity.this, DetalhesActivity.class);

        //criando os parametros e adicionando os dados do item selecionado
        Bundle params = new Bundle();
        params.putString("name", personagem.get("name"));
        params.putString("species", personagem.get("species"));
        params.putString("gender", personagem.get("gender"));
        params.putString("image", personagem.get("image"));

        //adicionando os parametros no caminho de tela
        telaDetalhes.putExtras(params);

        //abrindo a tela detalhes
        startActivity(telaDetalhes);
    }
}