package mata62.biblioteca.domain.models;

import mata62.biblioteca.domain.strategies.RegraEmprestimo;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {

    private String codigo; // 
    private String nome; // 
    
    private List<Emprestimo> emprestimos;
    private List<Reserva> reservas;

    public Usuario(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.emprestimos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
    
    /**
     * Retorna o número máximo de livros que o usuário pode emprestar.
     * @return
     */
    public abstract int getLimiteEmprestimos();

    /**
     * Retorna o prazo, em dias, que o usuário tem para devolver um livro.
     * @return
     */
    public abstract int getPrazoEmprestimoDias();

    /**
     * Retorna a estratégia de regra de empréstimo aplicável a este tipo de usuário.
     * @return
     */
    public abstract RegraEmprestimo getRegraEmprestimo();
}