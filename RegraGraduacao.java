public class RegraGraduacao implements RegraEmprestimo {
    @Override
    public int calcularPrazoEmprestimo() {
        return 3;
    }

    @Override
    public boolean validarEmprestimo() {
        return true;
    }
}
