package mata62.biblioteca.domain.commands;

import mata62.biblioteca.domain.models.Emprestimo;
import mata62.biblioteca.domain.models.Reserva;
import mata62.biblioteca.domain.models.Usuario;
import mata62.biblioteca.domain.repository.Repositorio;

public class ComandoConsultarUsuario implements Comando {

    @Override
    public String executar(String[] args) {
        if (args.length < 1) {
            return "Erro: Código do usuário não fornecido.";
        }
        String codigoUsuario = args[0];
        
        Usuario usuario = Repositorio.getInstance().buscarUsuarioPorCodigo(codigoUsuario);
        if (usuario == null) {
            return "Usuário não encontrado.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Usuário: ").append(usuario.getNome()).append("\n");
        sb.append("Código: ").append(usuario.getCodigo()).append("\n");
        sb.append("--- Empréstimos ---\n");
        if (usuario.getEmprestimos().isEmpty()) {
            sb.append("Nenhum empréstimo realizado.\n");
        } else {
            for (Emprestimo emprestimo : usuario.getEmprestimos()) {
                sb.append("  Livro: ").append(emprestimo.getExemplar().getLivro().getTitulo()).append("\n");
                sb.append("    Data do Empréstimo: ").append(emprestimo.getDataEmprestimo()).append("\n");
                if (emprestimo.estaEmCurso()) {
                    sb.append("    Status: Em curso\n");
                    sb.append("    Devolução Prevista: ").append(emprestimo.getDataPrevistaDevolucao()).append("\n");
                } else {
                    sb.append("    Status: Finalizado\n");
                    sb.append("    Devolvido em: ").append(emprestimo.getDataDevolucaoReal()).append("\n");
                }
            }
        }
        
        sb.append("--- Reservas ---\n");
        if (usuario.getReservas().isEmpty()) {
            sb.append("Nenhuma reserva ativa.\n");
        } else {
            for (Reserva reserva : usuario.getReservas()) {
                sb.append("  Livro: ").append(reserva.getLivro().getTitulo()).append("\n");
                sb.append("    Data da Reserva: ").append(reserva.getDataReserva()).append("\n");
            }
        }
        
        return sb.toString();
    }
}