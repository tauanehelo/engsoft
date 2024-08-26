import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Livro> livros;
    private List<Usuario> usuarios;

    public Sistema() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void processarComando() {
        Comunicacao.getInstance().processarComando(this);
    }

    public void executarReserva(Usuario usuario, Livro livro) {
        if (usuario.getReservas().size() >= 3) {
            Comunicacao.getInstance().getOutput("Reserva falhou. O usuário " + usuario.getNome() + " já tem 3 reservas.");
            return;
        }

        livro.adicionarReserva(usuario);
        usuario.adicionarReserva(livro);
        Comunicacao.getInstance().getOutput("Reserva realizada com sucesso. Usuário: " + usuario.getNome() + ", Livro: " + livro.getTitulo());
        }
    }

    public void executarDevolucao(Usuario usuario, Livro livro) {
        //falta verificar se a pessoa realmente pegou esse livro emprestado
        for (Exemplar exemplar : livro.getExemplares()) {
            if (exemplar.getStatus().equals("Emprestado")) {
                exemplar.setStatus("Disponível");
                Comunicacao.getInstance().getOutput("Devolução realizada com sucesso. Usuário: " + usuario.getNome() + ", Livro: " + livro.getTitulo());
                return;
            }
        }
        Comunicacao.getInstance().getOutput("Devolução falhou. Não há empréstimo em aberto para o usuário: " + usuario.getNome() + " e livro: " + livro.getTitulo());
    }
    

    public void guardarEmprestimo() {
    }

    public void consultaLivro(int codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.println("Título: " + livro.getTitulo());
        
        List<Usuario> usuariosReservaram = livro.getUsuariosReservaram();
        System.out.println("Quantidade de Reservas: " + usuariosReservaram.size());

        if (!usuariosReservaram.isEmpty()) {
            System.out.println("Usuários que realizaram reservas:");
            for (Usuario usuario : usuariosReservaram) {
                System.out.println("- " + usuario.getNome());
            }
        }
        
        List<Exemplar> exemplares = livro.getExemplares();
        System.out.println("Exemplares:");

        for (Exemplar exemplar : exemplares) {
            System.out.println("Código: " + exemplar.getCodigoExemplar() + ", Status: " + exemplar.getStatus());
        }
        //implementação da consulta de livros
    }

    public void consultaUsuario(Usuario usuario) {
        //implementação da consulta de usuários
    }

    public void consultaProfessor(Professor professor) {
        //implementação da consulta de professores
    }

    public void sair() {
        //implementação de encerramento do sistema
    }
}
