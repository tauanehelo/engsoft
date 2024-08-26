public class Professor implements Usuario, Observer {
    private String codigoUsuario;
    private String nome;
    private RegraProfessor regraEmprestimo;
    private int notificacoes;

    public Professor(String codigoUsuario, String nome) {
        this.codigoUsuario = codigoUsuario;
        this.nome = nome;
        this.regraEmprestimo = new RegraProfessor();
        this.notificacoes = 0;
    }

    @Override
    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public RegraEmprestimo getRegraEmprestimo() {
        return regraEmprestimo;
    }

    @Override
    public void notificar(String mensagem) {
        notificacoes++;
        System.out.println("Professor " + getNome() + " foi notificado: " + mensagem);
    }

    @Override
    public int getNotificacoes() {
        return notificacoes;
    }
}