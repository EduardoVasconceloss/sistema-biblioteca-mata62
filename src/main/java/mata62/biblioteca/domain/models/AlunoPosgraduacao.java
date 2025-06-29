package mata62.biblioteca.domain.models;

import mata62.biblioteca.domain.strategies.RegraEmprestimo;
import mata62.biblioteca.domain.strategies.RegraEmprestimoAluno;

public class AlunoPosgraduacao extends Usuario {
    
    public AlunoPosgraduacao(String codigo, String nome) {
        super(codigo, nome);
    }

    @Override
    public int getLimiteEmprestimos() {
        return 3;
    }

    @Override
    public int getPrazoEmprestimoDias() {
        return 5;
    }

    @Override
    public RegraEmprestimo getRegraEmprestimo() {
        return new RegraEmprestimoAluno();
    }
}