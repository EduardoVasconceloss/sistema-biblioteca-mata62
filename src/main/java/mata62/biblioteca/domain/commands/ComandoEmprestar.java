package mata62.biblioteca.domain.commands;

import java.time.LocalDate;
import java.util.Optional;
import mata62.biblioteca.domain.models.Emprestimo;
import mata62.biblioteca.domain.models.Exemplar;
import mata62.biblioteca.domain.models.Livro;
import mata62.biblioteca.domain.models.Usuario;
import mata62.biblioteca.domain.repository.Repositorio;
import mata62.biblioteca.domain.strategies.RegraEmprestimo;
import mata62.biblioteca.domain.strategies.RegraEmprestimoException;

public class ComandoEmprestar implements Comando {

    @Override
    public String executar(String[] args) { //
        if (args.length < 2) {
            return "Erro: Argumentos insuficientes. Formato esperado: emp <cod_usuario> <cod_livro>";
        }
        String codigoUsuario = args[0];
        String codigoLivro = args[1];

        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(codigoUsuario);
        if (usuario == null) {
            return "Erro: Usuário não encontrado.";
        }
        Livro livro = repositorio.buscarLivroPorCodigo(codigoLivro);
        if (livro == null) {
            return "Erro: Livro não encontrado.";
        }

        try {
            RegraEmprestimo regra = usuario.getRegraEmprestimo();
            regra.validar(usuario, livro);

            Optional<Exemplar> exemplarDisponivelOpt = livro.getExemplares().stream()
                .filter(Exemplar::estaDisponivel)
                .findFirst();

            if (!exemplarDisponivelOpt.isPresent()) {
                return "Não foi possível realizar o empréstimo, pois não há exemplares disponíveis.";
            }
            Exemplar exemplarParaEmprestar = exemplarDisponivelOpt.get();

            Emprestimo novoEmprestimo = new Emprestimo(usuario, exemplarParaEmprestar, LocalDate.now());
            usuario.getEmprestimos().add(novoEmprestimo);

            livro.getReservas().removeIf(reserva -> reserva.getUsuario().equals(usuario));

            return "Empréstimo do livro '" + livro.getTitulo() + "' realizado com sucesso para o usuário '" + usuario.getNome() + "'.";

        } catch (RegraEmprestimoException e) {
            return e.getMessage();
        }
    }
}