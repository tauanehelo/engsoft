import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main (String[] args) {
        Comunicacao comunicacao = Comunicacao.getInstance();
        Livro livro1 = new Livro("100", "Engenharia de Software", "AddisonWesley", Arrays.asList("Ian Sommervile"), "6", "2000");
        Livro livro2 = new Livro("101", "UML - Guia do Usuário", "Campus", Arrays.asList("Grady Booch", "James Rumbaugh", "Ivar Jacobson"), "7", "2000");
        Livro livro3 = new Livro("200", "Code Complete", "Microsoft Press", Arrays.asList("Steve McConnell"), "2", "2014");
        Livro livro4 = new Livro("201", "Agile Software Development, Principles, Patterns, and Practices", "Pretince Hall", Arrays.asList("Robert Martin"), "1", "2002");
        Livro livro5 = new Livro("300", "Refactoring: Improving the Design of Existing Code", "Addison-Wesley Professional", Arrays.asList("Martin Fowler"), "1", "1999");
        Livro livro6 = new Livro("301", "Software Metrics: A Rigorous and Practical Approach", "CRC Press", Arrays.asList("Norman Fenton", "James Bieman"), "3", "2014");
        Livro livro7 = new Livro("400", "Design Patterns: Elements of Reusable Object-Oriented Software", "Addison-Wesley Professional", Arrays.asList("Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"), "1", "1994");
        Livro livro8 = new Livro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison-Wesley Professional", Arrays.asList("Martin Fowler"), "3", "2003");
        
        AlunoGraduacao aluno1 = new AlunoGraduacao("123", "João da Silva");
        AlunoGraduacao aluno2 = new AlunoGraduacao("789", "Pedro Paulo");
        AlunoPosGraduacao alunopg = new AlunoPosGraduacao("456", "Luiz Fernando Rodrigues");
        Professor professor = new Professor("100", "Carlos Lucena");
        
        Exemplar exemplar1 = new Exemplar(livro1, "001");
        Exemplar exemplar2 = new Exemplar(livro1, "002");
        Exemplar exemplar3 = new Exemplar(livro2, "003");
        Exemplar exemplar4 = new Exemplar(livro3, "004");
        Exemplar exemplar5 = new Exemplar(livro4, "005");
        Exemplar exemplar6 = new Exemplar(livro5, "006");
        Exemplar exemplar7 = new Exemplar(livro5, "007");
        Exemplar exemplar8 = new Exemplar(livro7, "008");
        Exemplar exemplar9 = new Exemplar(livro7, "009");
        
        List<Livro> livros = Arrays.asList(livro1, livro2, livro3, livro4, livro5, livro6, livro7, livro8);
        List<Usuario> usuarios = Arrays.asList(aluno1, aluno2, alunopg, professor);
        List<Exemplar> exemplares = Arrays.asList(exemplar1, exemplar2, exemplar3, exemplar4, exemplar5, exemplar6, exemplar7, exemplar8, exemplar9);
        
        Sistema Sistema = new Sistema(livros, exemplares, usuarios);
        comunicacao.processarComando(Sistema);
    }
}
