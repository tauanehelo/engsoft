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

    public void setOutput(String mensagem) {
        System.out.println(mensagem);
    }

    public void processarComando(Sistema sistema) {
        String comando = getInput();
        String[] partes = comando.split(" ");
        
        if (partes.length < 3) {
            setOutput("Comando inválido.");
            this.processarComando( sistema);
            return;
        }

        String acao = partes[0];
        String codigoUsuario = partes[1];
        String codigoLivro = partes[2];

        if (codigoUsuario == null) {
            setOutput("Usuário não encontrado.");
            this.processarComando( sistema);
            return;
        }
        if (codigoLivro == null) {
            setOutput("Livro não encontrado.");
            this.processarComando( sistema);
            return;
        }

        switch (acao) {
            case "dev":
                sistema.executarDevolucao(codigoUsuario, codigoLivro);
 
            case "res":
                sistema.executarReserva(codigoUsuario, codigoLivro);

            default:
                setOutput("Comando desconhecido.");

        }
        this.processarComando( sistema);
    }
}

