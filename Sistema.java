import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private final List<Livro> livros;
    private final List<Exemplar> exemplares;
    private final List<Usuario> usuarios;
    private final List<Reserva> reservas;
    private final Comunicacao comunicacao = Comunicacao.getInstance();

    public Sistema(List<Livro> livros, List<Exemplar> exemplares, List<Usuario> usuarios) {
        this.livros = livros;
        this.exemplares = exemplares;
        this.usuarios = usuarios;
        this.reservas = new ArrayList<>();
    }

    private Usuario getUsuarioByCodigo(String codigoUsuario){
        for (Usuario usuario : this.usuarios){
            if (codigoUsuario.equals(usuario.getCodigoUsuario())){
                return usuario;
            }
        }
        return null;
    }
    private Livro getLivroByCodigo(String codigoLivro){
        for (Livro livro : this.livros){
            if (codigoLivro.equals(livro.getCodigo())){
                return livro;
            }
        }
        return null;
    }

    private List<Exemplar> getExemplaresByLivro(Livro livro){
        List<Exemplar> listaDeExemplares = new ArrayList<>();
        for (Exemplar exemplar : this.exemplares){
            if (livro == exemplar.getLivro()){
                listaDeExemplares.add(exemplar);
            }
        }
        return listaDeExemplares;
    }

    public void executarEmprestimo(String codigoUsuario, String codigoLivro) {
        Usuario usuario = getUsuarioByCodigo(codigoUsuario);
        if (usuario == null) {
            comunicacao.setOutput("Usuário não encontrado.");
            return;
        }
        Livro livro = getLivroByCodigo(codigoLivro);
        if (livro == null) {
            comunicacao.setOutput("Livro não encontrado.");
            return;
        }

        Reserva reservaRemover = null;
        for (Reserva reserva : this.reservas) {
            if (reserva.getUsuario().equals(usuario) && reserva.getLivro().equals(livro)) {
                reservaRemover = reserva;
                break;
            }
        }

        if (reservaRemover != null) {
            this.reservas.remove(reservaRemover);  
        }

        for (Exemplar exemplar : getExemplaresByLivro(livro)) {
            if ("Disponivel".equals(exemplar.getStatus())) {
                exemplar.emprestar(usuario);
                comunicacao.setOutput("Empréstimo realizado com sucesso. Usuário: " + usuario.getNome() + ", Livro: " + livro.getTitulo());
                return;
            }
        }
        comunicacao.setOutput("Empréstimo falhou. Não há exemplares disponíveis para o livro: " + livro.getTitulo());
    }

    public void executarReserva(String codigoUsuario, String codigoLivro) {
        Usuario usuario = getUsuarioByCodigo(codigoUsuario);
        if (usuario == null) {
            comunicacao.setOutput("Usuario não encontrado.");
            return;
        }
        Livro livro = getLivroByCodigo(codigoLivro);
        if (livro == null) {
            comunicacao.setOutput("Livro não encontrado.");
            return;
        }
        int reservasDoUsuario = 0;

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

    public void executarDevolucao(String codigoUsuario, String codigoLivro) {
        Usuario usuario = getUsuarioByCodigo(codigoUsuario);
        if (usuario == null) {
            comunicacao.setOutput("Usuario não encontrado.");
            return;
        }
        Livro livro = getLivroByCodigo(codigoLivro);
        if (livro == null) {
            comunicacao.setOutput("Livro não encontrado.");
            return;
        }
        for (Exemplar exemplar : getExemplaresByLivro(livro)) {
            if (exemplar.getDetentor().equals(usuario)) {
                exemplar.devolver();
                comunicacao.setOutput("Devolução realizada com sucesso. Usuário: " + usuario.getNome() + ", Livro: " + livro.getTitulo());
                return;
            }
        }
        comunicacao.setOutput("Devolução falhou. Não há empréstimo em aberto para o usuário: " + usuario.getNome() + " e livro: " + livro.getTitulo());
    }
    
    public void cadastraObservador(String codigoUsuario, String codigoLivro) {
        Usuario usuario = getUsuarioByCodigo(codigoUsuario);
        if (usuario == null) {
            comunicacao.setOutput("Usuario não encontrado.");
            return;
        }
        Livro livro = getLivroByCodigo(codigoLivro);
        if (livro == null) {
            comunicacao.setOutput("Livro não encontrado.");
            return;
        }
        if (usuario instanceof Observer observador) {
            livro.adicionarObservador(observador);
            comunicacao.setOutput("Observador " + usuario.getNome() + " cadastrado com sucesso no livro: " + livro.getTitulo());
        } else {
            comunicacao.setOutput("Usuário não pode ser um observador.");
        }
    }

    public void guardarEmprestimo(Usuario usuario, Livro livro) {
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
            comunicacao.setOutput("Livro não encontrado.");
            return;
        }

        comunicacao.setOutput("Título: " + livro.getTitulo());
        List<Usuario> usuariosReservaram = new ArrayList<>();
        for (Reserva reserva : this.reservas) {
            if (reserva.getLivro().equals(livro)){
                usuariosReservaram.add(reserva.getUsuario());
            }
        }
        comunicacao.setOutput("Quantidade de Reservas: " + usuariosReservaram.size());

        if (!usuariosReservaram.isEmpty()) {
            comunicacao.setOutput("Usuários que realizaram reservas:");
            for (Usuario usuario : usuariosReservaram) {
                comunicacao.setOutput("- " + usuario.getNome());
            }
        }
        
        comunicacao.setOutput("Exemplares:");

        for (Exemplar exemplar : getExemplaresByLivro(livro)) {
            String texto = "- Código: " + exemplar.getCodigoExemplar() + ", Status: " + exemplar.getStatus();
            
            if ("Emprestado".equals(exemplar.getStatus())) {
                texto += ", Atualmente com o usuário: " + exemplar.getDetentor().getNome() + 
                         ", Data de Empréstimo: " + exemplar.getDataEmprestimo() + 
                         ", Data de Devolução: " + exemplar.getDataDevolucao();
            }
            comunicacao.setOutput(texto);
        }
    }

    public void consultaUsuario(String codigoUsuario) {
        Usuario usuario = getUsuarioByCodigo(codigoUsuario);
    
        if (usuario == null) {
            comunicacao.setOutput("Usuário não encontrado.");
            return;
        }

        comunicacao.setOutput("Empréstimos:");
        for (Exemplar exemplar : this.exemplares) {
            if (exemplar.getDetentor() != null && exemplar.getDetentor().equals(usuario)) {
                String status = exemplar.isEmprestado() ? "Em curso" : "Finalizado";
                String dataDevolucao = exemplar.getDataDevolucao() != null ? exemplar.getDataDevolucao().toString() : "Não definida";
                comunicacao.setOutput("Livro: " + exemplar.getLivro().getTitulo() + ", Data do Empréstimo: " + exemplar.getDataEmprestimo() + ", Status: " + status + ", Data de Devolução: " + dataDevolucao);
            }
        }

        comunicacao.setOutput("Reservas:");
        for (Reserva reserva : this.reservas) {
            if (reserva.getUsuario().equals(usuario)) {
                comunicacao.setOutput("Livro: " + reserva.getLivro().getTitulo() + ", Data da Reserva: " + reserva.getDataReserva());
            }
        }
    }

    public void consultaProfessor(String codigoUsuario) {
        Usuario usuario = getUsuarioByCodigo(codigoUsuario);

        if (usuario == null) {
            comunicacao.setOutput("Professor não encontrado.");
            return;
        }

        if (usuario instanceof Professor professor) {
            int numeroNotificacoes = professor.getNotificacoes();
            comunicacao.setOutput("O professor " + professor.getNome() + " foi notificado " + numeroNotificacoes + " vezes.");
        } else {
            comunicacao.setOutput("Usuário não é um professor.");
        }
    }

}
