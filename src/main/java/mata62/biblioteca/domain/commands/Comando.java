package mata62.biblioteca.domain.commands;

/**
 * Interface (Command) para definir o contrato dos comandos do sistema.
 * Cada comando encapsula uma solicitação do usuário.
 */
public interface Comando {

    /**
     * Executa a ação do comando.
     * @param args
     * @return 
     */
    String executar(String[] args);
}