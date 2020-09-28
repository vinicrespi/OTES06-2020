package com.vinicius.chatandroid;

import android.util.Log;

import com.vinicius.chatandroid.db.Comunicador;
import com.vinicius.chatandroid.db.Interpretador;
import com.vinicius.chatandroid.db.MessageListener;
import com.vinicius.chatandroid.models.ListaUsuarios;
import com.vinicius.chatandroid.models.Usuario;

import java.util.List;

public class Application implements MessageListener {
    private static final Application instancia = new Application();
    public static Application getInstance() {return instancia;}

    private Comunicador comunicador;

    private Application() {
        comunicador = new Comunicador();
        comunicador.addListener(Interpretador.getInstance());
        Interpretador.getInstance().addObservador(this);
    }

    /**
     *
     * @param userId
     */
    public void enviarMensagemLogin(String userId) {
        // { "login": { "user-id":"o.professor" } }
        String header = "{ \"login\": { \"user-id\":\"";
        String tail   = "\" } }";
        String mensagem = header + userId + tail;
        //Atualizando o id do Usuario.
        Usuario.getInstance().setUserId(userId);
        comunicador.enfileraMensagem(mensagem);

    }

    @Override
    public void onListaDeUsuariosChegando(List<String> usuarios) {
        Log.d("LISTA", "onListaDeUsuariosChegando: " + usuarios.toString());
        ListaUsuarios.getListaDeUsuarios().set(usuarios);
    }

    @Override
    public void onMensagemChegando(String remetente, String texto) {
        //{ "message": { "sender":"o.professor", "receiver": "o.aluno", "content" : "uma mensagem inicial /success" } }
        String sender = "{ \"message\": { \"sender\":\"";
        String receiver = ", \"receiver\": \"";
        String content = ", \"content\": \"";
        String tail   = "\" } }";
        String senderUser = Usuario.getInstance().getUserId();
        String mensagem = sender + senderUser + receiver + remetente + content + texto + tail;
//        comunicador.falaComOServidor(mensagem);
    }

    @Override
    public void onMensagemDeErroChegando(String motivo) {

    }
}
