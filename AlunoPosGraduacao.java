public class AlunoPosGraduacao implements Usuario {
    private String codigoUsuario;
    private String nome;
    private RegraPosGraduacao regraEmprestimo;

    public AlunoPosGraduacao(String codigoUsuario, String nome) {
        this.codigoUsuario = codigoUsuario;
        this.nome = nome;
        this.regraEmprestimo = new RegraPosGraduacao();
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
}
