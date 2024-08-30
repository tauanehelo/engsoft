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
        int reservasLivro = 0;
        Reserva reservaRemover = null;
        for (Reserva reserva : this.reservas) {
            if (reserva.getLivro().equals(livro)){
                reservasLivro++;
                if (reservaRemover == null && reserva.getUsuario().equals(usuario)) {
                    reservaRemover = reserva;
                }
            }
        }
        
        Exemplar exemplarDisponivel = null;
        for (Exemplar exemplar : getExemplaresByLivro(livro)) {
            if ("Disponivel".equals(exemplar.getStatus())) {
                exemplarDisponivel = exemplar;
            }
        }
        if (exemplarDisponivel == null){
            comunicacao.setOutput("Empréstimo falhou. Não há exemplares disponíveis para o livro: " + livro.getTitulo());
        } else if (usuario.isDevedor()) {
            comunicacao.setOutput("Empréstimo falhou. O usuário está com livro em atraso.");
        } else if(usuario instanceof Professor) {
            if (reservaRemover != null ) {
                this.reservas.remove(reservaRemover);
            }
            exemplarDisponivel.emprestar(usuario);
            usuario.guardarEmprestimo(new Emprestimo(livro, usuario));
            comunicacao.setOutput("Empréstimo realizado com sucesso. Usuário: " + usuario.getNome() + ", Livro: " + livro.getTitulo());
        } else {
            if (!usuario.podePegarEmprestimo()){
                comunicacao.setOutput("Empréstimo falhou. O usuário já está com o limite de emprestimos em aberto.");
            } else if (reservaRemover == null && reservasLivro >= getExemplaresByLivro(livro).size()) {
                comunicacao.setOutput("Empréstimo falhou. O numero de Exemplares disponiveis é menor que o número de reservas e o usuario não tem uma reserva.");
            } else if (usuario.hasEmprestimoAberto(livro)) {
                comunicacao.setOutput("Empréstimo falhou. O usuário já está com um emprestimo do Livro:" + livro.getTitulo());
            } else {
                this.reservas.remove(reservaRemover);
                exemplarDisponivel.emprestar(usuario);
                usuario.guardarEmprestimo(new Emprestimo(livro, usuario));
                comunicacao.setOutput("Empréstimo realizado com sucesso. Usuário: " + usuario.getNome() + ", Livro: " + livro.getTitulo());
            }
        }
    }

    public void executarReserva(String codigoUsuario, String codigoLivro) {
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

        for (Reserva reserva : this.reservas) {
            if (reserva.getLivro().equals(livro) && reserva.getUsuario().equals(usuario)) {
                this.reservas.remove(reserva);
                break;
            }
        }
        int reservasDoUsuario = 0;
        int reservasSimutaneas = 0;

        for (Reserva reserva : this.reservas) {
            if (reserva.getUsuario().equals(usuario)){
                reservasDoUsuario++;
            }
            if (reserva.getLivro().equals(livro)){
                reservasSimutaneas++;
            }
        }
        if (reservasDoUsuario == 3) {
            comunicacao.setOutput("Reserva falhou. O usuário " + usuario.getNome() + " já tem 3 reservas ativas.");
            return;
        }
        if (reservasSimutaneas + 1 > 2){
            livro.notificarObservadores(); 
        }
        Reserva novaReserva = new Reserva(livro, usuario);
        this.reservas.add(novaReserva);
        usuario.guardarReserva(novaReserva);
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
            if (exemplar.getDetentor() != null && exemplar.getDetentor().equals(usuario)) {
                exemplar.devolver();
                usuario.devolverEmprestimo(livro);
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
                         ", Data de Empréstimo: " + exemplar.getDetentor().consultaDataEmprestimo(livro) +
                         ", Data de Devolução: " +  exemplar.getDetentor().consultaDataDevolucao(livro);
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
        for (Emprestimo emprestimo : usuario.getEmprestimosFeitos()) {
            String dataDevolucao = emprestimo.getDataDevolucao() != null ? emprestimo.getDataDevolucao().toString() : "Não definida";
            comunicacao.setOutput("Livro: " + emprestimo.getLivro().getTitulo() + ", Data do Empréstimo: " + emprestimo.getDataEmprestimo() + ", Status: " + emprestimo.getStatus() + ", Data de Devolução: " + dataDevolucao);
        }

        comunicacao.setOutput("Reservas:");
        for (Reserva reserva : usuario.getReservasFeitas()) {
            comunicacao.setOutput("Livro: " + reserva.getLivro().getTitulo() + ", Data da Reserva: " + reserva.getDataReserva());
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
