import java.util.Scanner;

public class Comunicacao {
    private static Comunicacao instance;
    private Scanner scanner;

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

    public void getOutput(String mensagem) {
        System.out.println(mensagem);
    }

    public void processarComando(Sistema sistema) {
        String comando = getInput();
        String[] partes = comando.split(" ");
        
        if (partes.length < 3) {
            getOutput("Comando inválido.");
            return;
        }

        String acao = partes[0];
        String codigoUsuario = partes[1];
        String codigoLivro = partes[2];

        if (usuario == null) {
            getOutput("Usuário não encontrado.");
            return;
        }
        if (livro == null) {
            getOutput("Livro não encontrado.");
            return;
        }

        switch (acao) {
            case "dev":
                sistema.executarDevolucao(usuario, livro);
                break;
            case "res":
                sistema.executarReserva(usuario, livro);
                break;
            default:
                getOutput("Comando desconhecido.");
                break;
        }
    }
}

