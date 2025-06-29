package mata62.biblioteca.domain.commands;

import java.time.LocalDate;
import mata62.biblioteca.domain.models.Livro;
import mata62.biblioteca.domain.models.Reserva;
import mata62.biblioteca.domain.models.Usuario;
import mata62.biblioteca.domain.repository.Repositorio;

public class ComandoReservar implements Comando {

    @Override
    public String executar(String[] args) {
        if (args.length < 2) {
            return "Erro: Argumentos insuficientes. Formato: res <cod_usuario> <cod_livro>";
        }
        String codigoUsuario = args[0];
        String codigoLivro = args[1];

        Repositorio rep = Repositorio.getInstance();
        Usuario usuario = rep.buscarUsuarioPorCodigo(codigoUsuario);
        if (usuario == null) return "Erro: Usuário não encontrado.";

        Livro livro = rep.buscarLivroPorCodigo(codigoLivro);
        if (livro == null) return "Erro: Livro não encontrado.";
        
        Reserva novaReserva = new Reserva(usuario, livro, LocalDate.now());
        usuario.getReservas().add(novaReserva);
        livro.adicionarReserva(novaReserva);

        return "Reserva do livro '" + livro.getTitulo() + "' realizada para o usuário '" + usuario.getNome() + "'.";
    }
}