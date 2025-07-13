package mata62.biblioteca.domain.commands;

public interface Comando {

    /**
     * Executa a ação do comando.
     * @param args
     * @return 
     */
    String executar(String[] args);
}