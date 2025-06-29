package mata62.biblioteca.domain.strategies;

/**
 * Exceção customizada para ser lançada quando uma regra de empréstimo não é atendida.
 */
public class RegraEmprestimoException extends Exception {
    public RegraEmprestimoException(String message) {
        super(message);
    }
}