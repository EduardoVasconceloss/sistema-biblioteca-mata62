package mata62.biblioteca.data;

import java.util.Arrays;
import mata62.biblioteca.domain.models.AlunoGraduacao;
import mata62.biblioteca.domain.models.AlunoPosgraduacao;
import mata62.biblioteca.domain.models.Exemplar;
import mata62.biblioteca.domain.models.Livro;
import mata62.biblioteca.domain.models.Professor;
import mata62.biblioteca.domain.repository.Repositorio;

public final class Setup {

    private Setup() {}

    public static void carregarDados() {
        Repositorio repositorio = Repositorio.getInstance();

        AlunoGraduacao joao = new AlunoGraduacao("123", "João da Silva");
        AlunoPosgraduacao luiz = new AlunoPosgraduacao("456", "Luiz Fernando Rodrigues");
        AlunoGraduacao pedro = new AlunoGraduacao("789", "Pedro Paulo");
        Professor carlos = new Professor("100", "Carlos Lucena");

        repositorio.addUsuario(joao);
        repositorio.addUsuario(luiz);
        repositorio.addUsuario(pedro);
        repositorio.addUsuario(carlos);

        Livro livro100 = new Livro("100", "Engenharia de Software", "Addison Wesley", Arrays.asList("Ian Sommervile"), 6, 2000);
        Livro livro101 = new Livro("101", "UML - Guia do Usuário", "Campus", Arrays.asList("Grady Booch", "James Rumbaugh", "Ivar Jacobson"), 7, 2000);
        Livro livro200 = new Livro("200", "Code Complete", "Microsoft Press", Arrays.asList("Steve McConnell"), 2, 2014);
        Livro livro201 = new Livro("201", "Agile Software Development, Principles, Patterns and Practices", "Prentice Hall", Arrays.asList("Robert Martin"), 1, 2002);
        Livro livro300 = new Livro("300", "Refactoring: Improving the Design of Existing Code", "Addison Wesley Professional", Arrays.asList("Martin Fowler"), 1, 1999);
        Livro livro301 = new Livro("301", "Software Metrics: A rigorous and Practical Approach", "CRC Press", Arrays.asList("Norman Fenton", "James Bieman"), 3, 2014);
        Livro livro400 = new Livro("400", "Design Patterns: Element of Reusable Object-Oriented Software", "Addison Wesley Professional", Arrays.asList("Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"), 1, 1994);
        Livro livro401 = new Livro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison Wesley Professional", Arrays.asList("Martin Fowler"), 3, 2003);
        
        livro100.adicionarExemplar(new Exemplar("01", livro100)); 
        livro100.adicionarExemplar(new Exemplar("02", livro100));
        livro101.adicionarExemplar(new Exemplar("03", livro101));
        livro200.adicionarExemplar(new Exemplar("04", livro200));
        livro201.adicionarExemplar(new Exemplar("05", livro201));
        livro300.adicionarExemplar(new Exemplar("06", livro300));
        livro300.adicionarExemplar(new Exemplar("07", livro300));
        livro400.adicionarExemplar(new Exemplar("08", livro400));
        livro400.adicionarExemplar(new Exemplar("09", livro400));

        repositorio.addLivro(livro100);
        repositorio.addLivro(livro101);
        repositorio.addLivro(livro200);
        repositorio.addLivro(livro201);
        repositorio.addLivro(livro300);
        repositorio.addLivro(livro301);
        repositorio.addLivro(livro400);
        repositorio.addLivro(livro401);
    }
}