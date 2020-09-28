package com.vinicius.chatandroid.models;

import androidx.databinding.ObservableField;

import java.util.List;

public class ListaMensagem {
    private static ObservableField<List<String>> listaDeMensagem = new ObservableField<List<String>>();

    public static ObservableField<List<String>> getListaDeMensagem() {
        return listaDeMensagem;
    }
}
