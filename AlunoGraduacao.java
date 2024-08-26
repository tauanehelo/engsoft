public class AlunoGraduacao extends Usuario {

    public AlunoGraduacao(String codigoUsuario, String nome) {
        super(codigoUsuario, nome);
        this.regraEmprestimo = new RegraGraduacao();
    }

}
