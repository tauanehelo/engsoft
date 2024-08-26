import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String codigo;
    private String titulo;
    private String editora;
    private List<String> autores;
    private String edicao;
    private String anoPublicacao;
    private List<Exemplar> exemplares;
    private List<Observer> observadores;
    private List<Usuario> usuariosReservaram;
    private List<Exemplar> exemplaresEmprestados;

    public Livro(String codigo, String titulo, String editora, List<String> autores, String edicao, String anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = new ArrayList<>();
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.exemplares = new ArrayList<>();
        this.observadores = new ArrayList<>();
        this.usuariosReservaram = new ArrayList<>();
        this.exemplaresEmprestados = new ArrayList<>();
        
    }

    public void adicionarObservador(Observer observador) {
        observadores.add(observador);
    }

    public void removerObservador(Observer observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(String mensagem) {
        for (Observer observer : observadores) {
            observer.notificar(mensagem);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Exemplar> getExemplares() {
        return exemplares;
    }

    public List<Usuario> getUsuariosReservaram() {
        return usuariosReservaram;
    }

    public void adicionarExemplaresEmprestados(Exemplar exemplar) {
        exemplares.add(exemplar);
    }
}
