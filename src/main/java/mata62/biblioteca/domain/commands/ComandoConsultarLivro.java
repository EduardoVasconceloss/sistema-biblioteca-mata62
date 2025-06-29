package mata62.biblioteca.domain.commands;

import mata62.biblioteca.domain.models.Exemplar;
import mata62.biblioteca.domain.models.Livro;
import mata62.biblioteca.domain.models.Reserva;
import mata62.biblioteca.domain.repository.Repositorio;

public class ComandoConsultarLivro implements Comando {

    @Override
    public String executar(String[] args) { //
        if (args.length < 1) {
            return "Erro: Código do livro não fornecido.";
        }
        String codigoLivro = args[0];
        
        Livro livro = Repositorio.getInstance().buscarLivroPorCodigo(codigoLivro);
        if (livro == null) {
            return "Livro não encontrado.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Título: ").append(livro.getTitulo()).append("\n");
        
        if (livro.getReservas().isEmpty()) {
            sb.append("Reservas: 0\n");
        } else {
            sb.append("Reservas: ").append(livro.getReservas().size()).append("\n");
            for (Reserva reserva : livro.getReservas()) {
                sb.append("  - ").append(reserva.getUsuario().getNome()).append("\n");
            }
        }
        
        sb.append("Exemplares:\n");
        for (Exemplar exemplar : livro.getExemplares()) {
            sb.append("  - Código: ").append(exemplar.getCodigoExemplar());
            if (exemplar.estaDisponivel()) {
                sb.append(", Status: Disponível\n");
            } else {
                sb.append(", Status: Emprestado para '")
                  .append(exemplar.getEmprestimoAtual().getUsuario().getNome())
                  .append("', Empréstimo em: ")
                  .append(exemplar.getEmprestimoAtual().getDataEmprestimo())
                  .append(", Devolução prevista para: ")
                  .append(exemplar.getEmprestimoAtual().getDataPrevistaDevolucao())
                  .append("\n");
            }
        }
        
        return sb.toString();
    }
}