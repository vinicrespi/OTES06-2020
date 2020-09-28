package com.vinicius.chatandroid.db;

import java.util.List;

public interface MessageListener {

    void onListaDeUsuariosChegando(List<String> usuarios);
    void onMensagemChegando(String remetente, String texto);
    void onMensagemDeErroChegando(String motivo);
}
