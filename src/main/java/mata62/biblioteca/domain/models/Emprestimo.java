package mata62.biblioteca.domain.models;

import java.time.LocalDate;

/**
 * Modela a operação de empréstimo de um exemplar para um usuário.
 */
public class Emprestimo {

    private Usuario usuario;
    private Exemplar exemplar;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucaoReal;

    public Emprestimo(Usuario usuario, Exemplar exemplar, LocalDate dataEmprestimo) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(usuario.getPrazoEmprestimoDias());
        this.dataDevolucaoReal = null;
        this.exemplar.setEmprestimoAtual(this);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }
    
    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public boolean estaEmCurso() {
        return dataDevolucaoReal == null;
    }

    public void finalizar() {
        this.dataDevolucaoReal = LocalDate.now();
        this.exemplar.setEmprestimoAtual(null);
    }
}