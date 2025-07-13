package mata62.biblioteca.domain.models;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    
    private String codigo;
    private String titulo;
    private String editora;
    private List<String> autores;
    private int edicao;
    private int anoPublicacao;
    private List<Exemplar> exemplares = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();
    private List<Professor> observadores = new ArrayList<>();

    public Livro(String codigo, String titulo, String editora, List<String> autores, int edicao, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Exemplar> getExemplares() {
        return exemplares;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
    
    public void adicionarExemplar(Exemplar exemplar) {
        this.exemplares.add(exemplar);
    }
    
    public void registrarObservador(Professor professor) {
        this.observadores.add(professor);
    }
    
    public void removerObservador(Professor professor) {
        this.observadores.remove(professor);
    }
    
    private void notificarObservadores() {
        for (Professor observador : this.observadores) {
            observador.notificar();
        }
    }
    
    /**
     * Adiciona uma reserva e verifica se deve notificar os observadores.
     * @param reserva
     */
    public void adicionarReserva(Reserva reserva) {
        this.reservas.add(reserva);
        if (this.reservas.size() > 2) { // 
            notificarObservadores();
        }
    }
}