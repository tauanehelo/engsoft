public class Professor extends Usuario implements Observer {
    private int notificacoes;

    public Professor(String codigoUsuario, String nome) {
        super(codigoUsuario, nome);
        this.regraEmprestimo = new RegraProfessor();
        this.notificacoes = 0;
    }

    @Override
    public void notificar() {
        this.notificacoes++;
    }

    @Override
    public int getNotificacoes() {
        return this.notificacoes;
    }
}