import java.util.Scanner;

public class Comunicacao {
    private static Comunicacao instance;
    private final Scanner scanner;

    private Comunicacao() {
        this.scanner = new Scanner(System.in);
    }

    public static Comunicacao getInstance() {
        if (instance == null) {
            instance = new Comunicacao();
        }
        return instance;
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void setOutput(String mensagem) {
        System.out.println(mensagem);
    }

    public void processarComando(Sistema sistema) {
        String comando = getInput();
        String[] partes = comando.split(" ");
        
        if (comando.equals("sai")) {
            setOutput("Sistema encerrado.");
            System.exit(0);
        }

        if (partes.length < 2) {
            setOutput("Comando inválido.");
            this.processarComando( sistema);
            return;
        }

        String acao = partes[0];
        String primeiroCodigo = partes[1];
        String segundoCodigo = partes.length > 2 ? partes[2] : null;

        if (primeiroCodigo == null) {
            setOutput("Usuário não informado.");
            this.processarComando( sistema);
            return;
        }

        switch (acao) {
            case "dev" -> sistema.executarDevolucao(primeiroCodigo, segundoCodigo);
            case "res" -> sistema.executarReserva(primeiroCodigo, segundoCodigo);
            case "obs" -> sistema.cadastraObservador(primeiroCodigo, segundoCodigo);
            case "liv" -> sistema.consultaLivro(primeiroCodigo);
            case "usu" -> sistema.consultaUsuario(primeiroCodigo);
            case "ntf" -> sistema.consultaProfessor(primeiroCodigo);
            case "emp" -> sistema.executarEmprestimo(primeiroCodigo, segundoCodigo);
            default -> setOutput("Comando desconhecido.");

        }
        
        this.processarComando(sistema);
    }
}

