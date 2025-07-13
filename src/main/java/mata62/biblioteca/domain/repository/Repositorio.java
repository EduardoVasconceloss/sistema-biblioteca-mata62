package mata62.biblioteca.domain.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import mata62.biblioteca.domain.models.Livro;
import mata62.biblioteca.domain.models.Usuario;

public final class Repositorio {

    private static final Repositorio instance = new Repositorio();

    private final Map<String, Usuario> usuarios;
    private final Map<String, Livro> livros;

    private Repositorio() {
        this.usuarios = new HashMap<>();
        this.livros = new HashMap<>();
    }

    public static Repositorio getInstance() {
        return instance;
    }

    public Usuario buscarUsuarioPorCodigo(String codigo) {
        return usuarios.get(codigo);
    }

    public Livro buscarLivroPorCodigo(String codigo) {
        return livros.get(codigo);
    }

    public void addUsuario(Usuario usuario) {
        this.usuarios.put(usuario.getCodigo(), usuario);
    }

    public void addLivro(Livro livro) {
        this.livros.put(livro.getCodigo(), livro);
    }
    
    public Collection<Usuario> getUsuarios() {
        return usuarios.values();
    }

    public Collection<Livro> getLivros() {
        return livros.values();
    }
}