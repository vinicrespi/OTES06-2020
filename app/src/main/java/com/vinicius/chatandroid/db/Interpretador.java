package com.vinicius.chatandroid.db;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class Interpretador implements ComunicadorListener {

    private static final Interpretador instance = new Interpretador();

    public static Interpretador getInstance() {
        return instance;
    }

    private final ArrayList<MessageListener> listaDeObservadores = new ArrayList<>(1);

    /**
     * Adicionando um observador para as mensagens.
     *
     * @param observador
     */
    public void addObservador(MessageListener observador) {
        listaDeObservadores.add(observador);
    }

    /**
     * Remove um observador da lista de Observadores.
     *
     * @param observador
     */
    public void removeObservador(MessageListener observador) {
        listaDeObservadores.remove(observador);
    }

    /**
     * Construtor.
     */
    public Interpretador() {

    }


    /**
     * Tipos de mensagens:
     * { "online-users": [ "broadcast", "o.professor", "o.aluno", "outro.aluno", "mais.um.aluno" ] }
     * { "message": { "sender":"o.professor", "receiver": "o.aluno", "content" : "uma mensagem inicial /success" } }
     * { "error": { "message":"user not found” } }
     *
     * @param message
     */
    @Override
    public void onMenssagemChegandoDoServidor(String message) {
        // transforma a mensagem para JsonObject para poder identificar qual mensagem chegou.
        JsonObject jsonObject = JsonParser.parseString(message).getAsJsonObject();
        // Se for uma mensagem de usuarios online...
        if (jsonObject.has("online-users")) {
            //se for uma mensagem com a lista de usuarios, extrai o array de dentro do Json e chama os listeners.
            JsonArray array = jsonObject.get("online-users").getAsJsonArray();
            ArrayList<String> reply = new ArrayList<>(1);
            // para cada elemento no JsonArray, copia para o reply.
            for (JsonElement element : array) {
                reply.add(element.getAsString());
            }
            // chamando os listeners.
            for (MessageListener observador : listaDeObservadores) {
                observador.onListaDeUsuariosChegando(reply);
            }
        }
        // se for uma mensagem
        else if (jsonObject.has("message")) {
            JsonObject mensagem = jsonObject.get("message").getAsJsonObject();
            String remetente = mensagem.get("sender").getAsString();
            String texto = mensagem.get("content").getAsString();
            // chamando os listeners.
            for (MessageListener observador : listaDeObservadores) {
                observador.onMensagemChegando(remetente, texto);
            }
        }
        // se for uma mensagem de erro.
        else if (jsonObject.has("error")) {
            JsonObject error = jsonObject.get("error").getAsJsonObject();
            String motivo = error.get("message").getAsString();
            // chamando os listeners.
            for (MessageListener observador : listaDeObservadores) {
                observador.onMensagemDeErroChegando(motivo);
            }
        }
    }
}