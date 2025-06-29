package mata62.biblioteca;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import mata62.biblioteca.data.Setup;
import mata62.biblioteca.domain.commands.Comando;
import mata62.biblioteca.domain.commands.ComandoEmprestar;
import mata62.biblioteca.domain.commands.ComandoConsultarLivro;
import mata62.biblioteca.domain.commands.ComandoConsultarUsuario;
import mata62.biblioteca.domain.commands.ComandoReservar;
import mata62.biblioteca.domain.commands.ComandoDevolver;
import mata62.biblioteca.domain.commands.ComandoObservar;
import mata62.biblioteca.domain.commands.ComandoConsultarNotificacoes;

public class Main {

    private static final Map<String, Comando> comandos = new HashMap<>();

    static {
        comandos.put("emp", new ComandoEmprestar());
        comandos.put("liv", new ComandoConsultarLivro());
        comandos.put("usu", new ComandoConsultarUsuario());
        comandos.put("res", new ComandoReservar());
        comandos.put("dev", new ComandoDevolver());
        comandos.put("obs", new ComandoObservar());
        comandos.put("ntf", new ComandoConsultarNotificacoes());
    }

    public static void main(String[] args) {
        Setup.carregarDados();
        System.out.println("Sistema de Biblioteca iniciado. Dados carregados.");
        System.out.println("Digite um comando ou 'sai' para terminar.");

        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("> ");
            String linha = scanner.nextLine();

            if (linha == null || linha.trim().isEmpty()) {
                continue;
            }

            if (linha.trim().equalsIgnoreCase("sai")) { 
                System.out.println("Sistema finalizado.");
                break;
            }

            String[] partes = linha.trim().split("\\s+");
            String chaveComando = partes[0].toLowerCase();
            String[] argumentos = Arrays.copyOfRange(partes, 1, partes.length);
            
            Comando comando = comandos.get(chaveComando);
            if (comando == null) {
                System.out.println("Comando desconhecido.");
            } else {
                String resultado = comando.executar(argumentos);
                System.out.println(resultado);
            }
        }

        scanner.close();
    }
}