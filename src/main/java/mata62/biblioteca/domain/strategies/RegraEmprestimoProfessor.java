package mata62.biblioteca.domain.strategies;

import java.time.LocalDate;
import mata62.biblioteca.domain.models.Emprestimo;
import mata62.biblioteca.domain.models.Livro;
import mata62.biblioteca.domain.models.Usuario;

public class RegraEmprestimoProfessor implements RegraEmprestimo {
    
    @Override
    public void validar(Usuario usuario, Livro livro) throws RegraEmprestimoException {
        for (Emprestimo e : usuario.getEmprestimos()) {
            if (e.estaEmCurso() && e.getDataPrevistaDevolucao().isBefore(LocalDate.now())) {
                throw new RegraEmprestimoException("Não foi possível realizar o empréstimo, pois o professor possui livros em atraso."); //
            }
        }
    }
}