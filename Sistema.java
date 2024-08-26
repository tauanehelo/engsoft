import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Livro> livros;
    private List<Exemplar> exemplares;
    private List<Usuario> usuarios;
    private List<Reserva> reservas;
    private Comunicacao comunicacao = Comunicacao.getInstance();

    public Sistema(List<Livro> livros, List<Exemplar> exemplares, List<Usuario> usuarios) {
        this.livros = livros;
        this.exemplares = exemplares;
        this.usuarios = usuarios;
        this.reservas = new ArrayList<Reserva>();
    }

    private Usuario getUsuarioByCodigo(String codigoUsuario){
        for (Usuario usuario : this.usuarios){
            if (codigoUsuario == usuario.getCodigoUsuario()){
                return usuario;
            }
        }
        return null;
    }
    private Livro getLivroByCodigo(String codigoLivro){
        for (Livro livro : this.livros){
            if (codigoLivro == livro.getCodigo()){
                return livro;
            }
        }
        return null;
    }

    private List<Exemplar> getExemplaresByLivro(Livro livro){
        List<Exemplar> listaDeExemplares = new ArrayList<Exemplar>();
        for (Exemplar exemplar : this.exemplares){
            if (livro == exemplar.getLivro()){
                listaDeExemplares.add(exemplar);
            }
        }
        return listaDeExemplares;
    }

    public void executarReserva(String codigoUsuario, String codigoLivro) {
        Usuario usuario = getUsuarioByCodigo(codigoUsuario);
        Livro livro = getLivroByCodigo(codigoLivro);
        int reservasDoUsuario = 0;
        if (usuario != null && livro != null){
            for (Reserva reserva : this.reservas) {
                if (reserva.getUsuario().equals(usuario)){
                    reservasDoUsuario++;
                }
            }
            if (reservasDoUsuario == 3) {
                comunicacao.setOutput("Reserva falhou. O usuário " + usuario.getNome() + " já tem 3 reservas.");
                return;
            }
            this.reservas.add(new Reserva(livro, usuario));
            comunicacao.setOutput("Reserva realizada com sucesso. Usuário: " + usuario.getNome() + ", Livro: " + livro.getTitulo());
        }
    }

    public void executarDevolucao(String codigoUsuario, String codigoLivro) {
        Usuario usuario = getUsuarioByCodigo(codigoUsuario);
        Livro livro = getLivroByCodigo(codigoLivro);
        for (Exemplar exemplar : getExemplaresByLivro(livro)) {
            if (exemplar.getDetentor().equals(usuario)) {
                exemplar.devolver();
                comunicacao.setOutput("Devolução realizada com sucesso. Usuário: " + usuario.getNome() + ", Livro: " + livro.getTitulo());
                return;
            }
        }
        comunicacao.setOutput("Devolução falhou. Não há empréstimo em aberto para o usuário: " + usuario.getNome() + " e livro: " + livro.getTitulo());
    }
    

    public void guardarEmprestimo(Usuario usuario, Livro livro) {
        // falta fazer as condições todas de emprestimo kkkkk
        for (Exemplar exemplar : getExemplaresByLivro(livro)) {
            if (exemplar.getStatus().equals("Disponivel")) {
                exemplar.emprestar(usuario);
                comunicacao.setOutput("Emprestimo realizado com sucesso. Usuário: " + usuario.getNome() + ", Livro: " + livro.getTitulo());
                return;
            }
        }
        comunicacao.setOutput("Emprestimo falhou. Não há empréstimo em aberto para o usuário: " + usuario.getNome() + " e livro: " + livro.getTitulo());

    }

    public void consultaLivro(String codigoLivro) {
        Livro livro = getLivroByCodigo(codigoLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.println("Título: " + livro.getTitulo());
        List<Usuario> usuariosReservaram = new ArrayList<Usuario>();
        for (Reserva reserva : this.reservas) {
            if (reserva.getLivro().equals(livro)){
                usuariosReservaram.add(reserva.getUsuario());
            }
        }
        System.out.println("Quantidade de Reservas: " + usuariosReservaram.size());

        if (!usuariosReservaram.isEmpty()) {
            System.out.println("Usuários que realizaram reservas:");
            for (Usuario usuario : usuariosReservaram) {
                System.out.println("- " + usuario.getNome());
            }
        }
        
        System.out.println("Exemplares:");

        for (Exemplar exemplar : getExemplaresByLivro(livro)) {
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
