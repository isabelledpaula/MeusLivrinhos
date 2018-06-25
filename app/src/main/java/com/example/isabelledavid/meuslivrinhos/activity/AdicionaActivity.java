package com.example.isabelledavid.meuslivrinhos.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.isabelledavid.meuslivrinhos.R;
import com.example.isabelledavid.meuslivrinhos.model.Livro;

import io.realm.Realm;

public class AdicionaActivity extends AppCompatActivity {

    private EditText edtxtNomeLivro;
    private EditText edtxtDescLivro;

    private Realm realm;

    private FloatingActionButton fabAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Adicionar Livro");

        realm = Realm.getDefaultInstance();

        edtxtNomeLivro = findViewById(R.id.edtxtNomeLivro);
        edtxtDescLivro = findViewById(R.id.edtxtDescLivro);
        fabAdicionar = findViewById(R.id.fabAdicionar);

        fabAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Number currentIdNum = realm.where(Livro.class).max("id");
                int nextId;
                if (currentIdNum == null) {
                    nextId = 0;
                } else {
                    nextId = currentIdNum.intValue() + 1;
                }

                Livro livro = new Livro();
                livro.setId(nextId);
                livro.setNome(edtxtNomeLivro.getText().toString());
                livro.setDescricao(edtxtDescLivro.getText().toString());

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(livro);
                realm.commitTransaction();

                finish();
            }
        });
    }

}
