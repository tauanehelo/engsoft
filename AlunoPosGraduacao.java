public class AlunoPosGraduacao extends Usuario {

    public AlunoPosGraduacao(String codigoUsuario, String nome) {
        super(codigoUsuario, nome);
        this.regraEmprestimo = new RegraPosGraduacao();
    }

}
