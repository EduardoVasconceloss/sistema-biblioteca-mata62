package mata62.biblioteca.domain.commands;

import mata62.biblioteca.domain.models.Professor;
import mata62.biblioteca.domain.models.Usuario;
import mata62.biblioteca.domain.repository.Repositorio;

public class ComandoConsultarNotificacoes implements Comando {

    @Override
    public String executar(String[] args) { //
        if (args.length < 1) {
            return "Erro: Código do usuário não fornecido.";
        }
        String codigoUsuario = args[0];

        Repositorio rep = Repositorio.getInstance();
        Usuario usuario = rep.buscarUsuarioPorCodigo(codigoUsuario);
        if (usuario == null) return "Erro: Usuário não encontrado.";
        
        if (!(usuario instanceof Professor)) {
            return "Erro: Apenas professores recebem notificações.";
        }

        int contagem = ((Professor) usuario).getNotificacoesRecebidas();

        return "O professor " + usuario.getNome() + " foi notificado " + contagem + " vez(es).";
    }
}