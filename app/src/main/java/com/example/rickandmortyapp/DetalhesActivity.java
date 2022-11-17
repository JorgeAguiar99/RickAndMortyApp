package com.example.rickandmortyapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalhesActivity extends AppCompatActivity {

    TextView name;
    TextView species;
    TextView gender;
    ImageView image;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        name = findViewById(R.id.txtNome);
        species = findViewById(R.id.txtEspecie);
        gender = findViewById(R.id.txtGenero);
        image = findViewById(R.id.imageView);

        //capturando o caminho de tela utilizado para abrir esta tela
        Intent dadosRecebidos = getIntent();
        if (dadosRecebidos != null) {
            //capturando os dados recebidos no caminho de tela
            Bundle params = dadosRecebidos.getExtras();
            if (params != null) {
                //adicionando o nome do personagem no texto da tela
                name.setText(params.getString("name"));
                species.setText(params.getString("species"));
                gender.setText(params.getString("gender"));

                //carregando a imagem da web no objeto imagem da tela
                new DownloadImagem(image).execute(params.getString("image"));
            }
        }
    }
}