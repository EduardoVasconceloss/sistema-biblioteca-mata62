package mata62.biblioteca.domain.strategies;

import java.time.LocalDate;
import mata62.biblioteca.domain.models.Emprestimo;
import mata62.biblioteca.domain.models.Livro;
import mata62.biblioteca.domain.models.Usuario;

public class RegraEmprestimoAluno implements RegraEmprestimo {

    @Override
    public void validar(Usuario usuario, Livro livro) throws RegraEmprestimoException {
        for (Emprestimo e : usuario.getEmprestimos()) {
            if (e.estaEmCurso() && e.getDataPrevistaDevolucao().isBefore(LocalDate.now())) {
                throw new RegraEmprestimoException("Não foi possível realizar o empréstimo, pois o usuário possui livros em atraso."); //
            }
        }
        
        long emprestimosAtuais = usuario.getEmprestimos().stream().filter(Emprestimo::estaEmCurso).count();
        if (emprestimosAtuais >= usuario.getLimiteEmprestimos()) {
            throw new RegraEmprestimoException("Não foi possível realizar o empréstimo. Usuário atingiu o limite de " + usuario.getLimiteEmprestimos() + " livros."); //
        }

        for (Emprestimo e : usuario.getEmprestimos()) {
            if (e.estaEmCurso() && e.getExemplar().getLivro().equals(livro)) {
                throw new RegraEmprestimoException("Não foi possível realizar o empréstimo, pois o usuário já tem um exemplar deste mesmo livro em empréstimo no momento."); //
            }
        }
        
        long exemplaresDisponiveis = livro.getExemplares().stream().filter(e -> e.estaDisponivel()).count();
        int totalReservas = livro.getReservas().size();
        
        boolean usuarioTemReserva = livro.getReservas().stream().anyMatch(r -> r.getUsuario().equals(usuario));

        if (totalReservas >= exemplaresDisponiveis && !usuarioTemReserva) {
             throw new RegraEmprestimoException("Não foi possível realizar o empréstimo. A quantidade de reservas é maior ou igual à de exemplares disponíveis."); //
        }
    }
}