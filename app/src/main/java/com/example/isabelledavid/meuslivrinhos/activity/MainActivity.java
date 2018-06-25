package com.example.isabelledavid.meuslivrinhos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.isabelledavid.meuslivrinhos.adapter.LivrosAdapter;
import com.example.isabelledavid.meuslivrinhos.R;
import com.example.isabelledavid.meuslivrinhos.model.Livro;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvLivros;
    private LivrosAdapter mAdapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Meus Livrinhos");

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AdicionaActivity.class);
                startActivity(i);
            }
        });

        cargaInicial();
        setupRecycler();
        
    }

    private void cargaInicial() {

        Livro livro1 = new Livro();
        livro1.setId(0);
        livro1.setNome("Dom Casmurro");
        livro1.setDescricao("Publicado pela primeira vez em 1899, “Dom Casmurro” é uma das grandes obras de Machado de Assis e confirma o olhar certeiro e crítico que o autor estendia sobre toda a sociedade brasileira. Também a temática do ciúme, abordada com brilhantismo nesse livro, provoca polêmicas em torno do caráter de uma das principais personagens femininas da literatura brasileira: Capitu.");

        Livro livro2 = new Livro();
        livro2.setId(1);
        livro2.setNome("Memórias Póstumas de Brás Cubas");
        livro2.setDescricao("Ao criar um narrador que resolve contar sua vida depois de morto, Machado de Assis muda radicalmente o panorama da literatura brasileira, além de expor de forma irônica os privilégios da elite da época.");

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(livro1);
        realm.copyToRealmOrUpdate(livro2);
        realm.commitTransaction();

    }

    private void setupRecycler() {
        rvLivros = findViewById(R.id.rvLivros);

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvLivros.setLayoutManager(layoutManager);

        RealmResults<Livro> livros = realm.where(Livro.class).findAll();

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new LivrosAdapter(this, livros);
        rvLivros.setAdapter(mAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        rvLivros.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
