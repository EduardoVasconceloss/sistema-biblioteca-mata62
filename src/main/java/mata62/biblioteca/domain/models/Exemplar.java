package mata62.biblioteca.domain.models;

public class Exemplar {
    
    private String codigoExemplar;
    private Livro livro;
    private Emprestimo emprestimoAtual;

    public Exemplar(String codigoExemplar, Livro livro) {
        this.codigoExemplar = codigoExemplar;
        this.livro = livro;
        this.emprestimoAtual = null;
    }
    
    public String getCodigoExemplar() {
        return codigoExemplar;
    }
    
    public Livro getLivro() {
        return livro;
    }

    public Emprestimo getEmprestimoAtual() {
        return emprestimoAtual;
    }

    public void setEmprestimoAtual(Emprestimo emprestimoAtual) {
        this.emprestimoAtual = emprestimoAtual;
    }
    
    public boolean estaDisponivel() {
        return this.emprestimoAtual == null;
    }
}