package mata62.biblioteca.domain.strategies;

import mata62.biblioteca.domain.models.Livro;
import mata62.biblioteca.domain.models.Usuario;

public interface RegraEmprestimo {

    /**
     * Valida se um usuário pode pegar um livro emprestado, de acordo com as regras.
     * @param usuario
     * @param livro
     * @throws RegraEmprestimoException
     */
    void validar(Usuario usuario, Livro livro) throws RegraEmprestimoException;
}