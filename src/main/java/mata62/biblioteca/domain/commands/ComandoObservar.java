package mata62.biblioteca.domain.commands;

import mata62.biblioteca.domain.models.Livro;
import mata62.biblioteca.domain.models.Professor;
import mata62.biblioteca.domain.models.Usuario;
import mata62.biblioteca.domain.repository.Repositorio;

public class ComandoObservar implements Comando {

    @Override
    public String executar(String[] args) { //
        if (args.length < 2) {
            return "Erro: Argumentos insuficientes. Formato: obs <cod_usuario> <cod_livro>";
        }
        String codigoUsuario = args[0];
        String codigoLivro = args[1];

        Repositorio rep = Repositorio.getInstance();
        Usuario usuario = rep.buscarUsuarioPorCodigo(codigoUsuario);
        if (usuario == null) return "Erro: Usuário não encontrado.";
        
        if (!(usuario instanceof Professor)) {
            return "Erro: Apenas professores podem observar livros.";
        }

        Livro livro = rep.buscarLivroPorCodigo(codigoLivro);
        if (livro == null) return "Erro: Livro não encontrado.";
        
        livro.registrarObservador((Professor) usuario);

        return "Professor '" + usuario.getNome() + "' agora está observando o livro '" + livro.getTitulo() + "'.";
    }
}