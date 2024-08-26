import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main (String[] args) {
        Comunicacao comunicacao = Comunicacao.getInstance();
        Livro livro1 = new Livro("100", "Engenharia de Software", "AddisonWesley", Arrays.asList("Ian Sommervile"), "6", "2000");
        List<Livro> livros = Arrays.asList(livro1);
        List<Usuario> usuarios = Arrays.asList(new AlunoGraduacao("123", "João da Silva"));
        List<Exemplar> exemplares = Arrays.asList(new Exemplar(livro1, "001"));
        Sistema Sistema = new Sistema(livros, exemplares, usuarios);
        comunicacao.processarComando(Sistema);
    }
}
