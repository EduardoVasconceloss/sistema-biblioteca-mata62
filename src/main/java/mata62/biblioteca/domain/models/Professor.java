package mata62.biblioteca.domain.models;

import mata62.biblioteca.domain.strategies.RegraEmprestimo;
import mata62.biblioteca.domain.strategies.RegraEmprestimoProfessor;

public class Professor extends Usuario {
    
    private int notificacoesRecebidas;

    public Professor(String codigo, String nome) {
        super(codigo, nome);
        this.notificacoesRecebidas = 0;
    }

    @Override
    public int getLimiteEmprestimos() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getPrazoEmprestimoDias() {
        return 8;
    }

    @Override
    public RegraEmprestimo getRegraEmprestimo() {
        return new RegraEmprestimoProfessor();
    }
    
    public int getNotificacoesRecebidas() {
        return notificacoesRecebidas;
    }

    public void notificar() {
        this.notificacoesRecebidas++;
    }
}