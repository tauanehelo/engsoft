public class AlunoGraduacao implements Usuario {
    private String codigoUsuario;
    private String nome;
    private RegraGraduacao regraEmprestimo;

    public AlunoGraduacao(String codigoUsuario, String nome) {
        this.codigoUsuario = codigoUsuario;
        this.nome = nome;
        this.regraEmprestimo = new RegraGraduacao();
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
