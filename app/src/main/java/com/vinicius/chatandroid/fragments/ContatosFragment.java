package com.vinicius.chatandroid.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vinicius.chatandroid.activities.MessageActivity;
import com.vinicius.chatandroid.models.ListaUsuarios;
import com.vinicius.chatandroid.R;

import java.util.List;

public class ContatosFragment extends Fragment {

    List<String> lista;
    MyAdapter myAdapter;

    public ContatosFragment() {
        super();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contatos, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView listaContato = getActivity().findViewById(R.id.listaContatos);
        myAdapter = new MyAdapter();
        listaContato.setAdapter(myAdapter);
        listaContato.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectUser = (String) view.getTag();
                Context context = view.getContext();
                Intent intent = new Intent(context, MessageActivity.class);
                intent.putExtra("selected", selectUser);

                context.startActivity(intent);
            }
        }));
        //
        ListaUsuarios.getListaDeUsuarios().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                                                                            @Override
                                                                            public void onPropertyChanged(final Observable sender, int propertyId) {
                                                                                Runnable r = new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        lista = ((ObservableField<List<String>>) sender).get();
                                                                                        myAdapter.notifyDataSetChanged();
                                                                                    }
                                                                                };
                                                                                getActivity().runOnUiThread(r);
                                                                            }
                                                                        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(lista != null) return lista.size();
            return 0;
        }

        @Override
        public Object getItem(int i) {
            if(lista != null) return lista.get(i);
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup container) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.linha_usuario, container, false);
            }
            if(lista != null) {
                String contato = lista.get(position);

                ((TextView) view.findViewById(R.id.txtUserName)).setText(contato);
            }

            return view;
        }
    }
}