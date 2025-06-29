package mata62.biblioteca.domain.commands;

import java.util.Optional;
import mata62.biblioteca.domain.models.Emprestimo;
import mata62.biblioteca.domain.models.Livro;
import mata62.biblioteca.domain.models.Usuario;
import mata62.biblioteca.domain.repository.Repositorio;

public class ComandoDevolver implements Comando {

    @Override
    public String executar(String[] args) { //
        if (args.length < 2) {
            return "Erro: Argumentos insuficientes. Formato: dev <cod_usuario> <cod_livro>";
        }
        String codigoUsuario = args[0];
        String codigoLivro = args[1];

        Repositorio rep = Repositorio.getInstance();
        Usuario usuario = rep.buscarUsuarioPorCodigo(codigoUsuario);
        if (usuario == null) return "Erro: Usuário não encontrado.";
        
        Optional<Emprestimo> emprestimoAtivoOpt = usuario.getEmprestimos().stream()
                .filter(e -> e.estaEmCurso() && e.getExemplar().getLivro().getCodigo().equals(codigoLivro))
                .findFirst();

        if (!emprestimoAtivoOpt.isPresent()) {
            return "Erro: Empréstimo não encontrado para este usuário e livro.";
        }

        Emprestimo emprestimoParaDevolver = emprestimoAtivoOpt.get();
        emprestimoParaDevolver.finalizar();

        return "Devolução do livro '" + emprestimoParaDevolver.getExemplar().getLivro().getTitulo() + "' realizada com sucesso.";
    }
}