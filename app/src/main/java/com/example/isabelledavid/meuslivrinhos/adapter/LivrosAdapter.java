package com.example.isabelledavid.meuslivrinhos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.isabelledavid.meuslivrinhos.R;
import com.example.isabelledavid.meuslivrinhos.model.Livro;

import io.realm.RealmResults;

public class LivrosAdapter extends RecyclerView.Adapter<LivrosAdapter.ViewHolder> {

    private Context context;
    private RealmResults<Livro> livros;

    public LivrosAdapter(Context context, RealmResults livros) {
        this.context = context;
        this.livros = livros;
    }

    @NonNull
    @Override
    public LivrosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_livro, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LivrosAdapter.ViewHolder holder, int position) {
        Livro livro = livros.get(position);

        holder.txtNomeLivro.setText(livro.getNome());
        holder.txtDescLivro.setText(livro.getDescricao());
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNomeLivro;
        private TextView txtDescLivro;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNomeLivro = itemView.findViewById(R.id.txtNomeLivro);
            txtDescLivro = itemView.findViewById(R.id.txtDescLivro);
        }
    }
}
