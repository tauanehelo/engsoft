import java.util.ArrayList;
import java.util.List;

public class Livro {
    private final String codigo;
    private final String titulo;
    private final String editora;
    private final List<String> autores;
    private final String edicao;
    private final String anoPublicacao;
    private List<Observer> observadores;

    public Livro(String codigo, String titulo, String editora, List<String> autores, String edicao, String anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.observadores = new ArrayList<>();
        
    }

    public void adicionarObservador(Observer observador) {
        this.observadores.add(observador);
    }

    public void removerObservador(Observer observador) {
        this.observadores.remove(observador);
    }

    public void notificarObservadores() {
        for (Observer observer : observadores) {
            observer.notificar();
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }
}
