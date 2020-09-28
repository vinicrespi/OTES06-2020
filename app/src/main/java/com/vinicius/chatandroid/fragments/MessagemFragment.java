package com.vinicius.chatandroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;

import com.vinicius.chatandroid.R;
import com.vinicius.chatandroid.models.ListaMensagem;
import com.vinicius.chatandroid.models.Message;

import java.util.List;

public class MessagemFragment extends Fragment {
    List<Message> lista;
    ChatDisplayer myAdapter;
    public static final String ARG_ITEM_ID = "selected";
    String selectedUserToChat;

    public MessagemFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            selectedUserToChat = getArguments().getString(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_message, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView listaMensagem = getActivity().findViewById(R.id.messageList);
        myAdapter = new ChatDisplayer();
        listaMensagem.setAdapter((ListAdapter) myAdapter);

        ListaMensagem.getListaDeMensagem().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(final Observable sender, int propertyId) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        lista = ((ObservableField<List<Message>>) sender).get();
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

    private class ChatDisplayer extends BaseAdapter {

        @Override
        public int getCount() {
            if(lista != null) return lista.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if(lista != null) return lista.get(position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup container) {
                Message msg = lista.get(position);
                if (selectedUserToChat.equals(msg.sender)) {
                    view = getLayoutInflater().inflate(R.layout.bubble_left, null);
                    ((TextView) view.findViewById(R.id.username)).setText(msg.sender);

                } else {
                    view = getLayoutInflater().inflate(R.layout.bubble_right, null);
                    ((TextView) view.findViewById(R.id.status)).setText(msg.status);

                }

                ((TextView) view.findViewById(R.id.msg)).setText(msg.content);

                return view;
        }
    }
}
