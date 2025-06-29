package mata62.biblioteca.domain.models;

import mata62.biblioteca.domain.strategies.RegraEmprestimo;
import mata62.biblioteca.domain.strategies.RegraEmprestimoAluno;

public class AlunoGraduacao extends Usuario {

    public AlunoGraduacao(String codigo, String nome) {
        super(codigo, nome);
    }

    @Override
    public int getLimiteEmprestimos() {
        return 2;
    }

    @Override
    public int getPrazoEmprestimoDias() {
        return 4;
    }

    @Override
    public RegraEmprestimo getRegraEmprestimo() {
        return new RegraEmprestimoAluno();
    }
}