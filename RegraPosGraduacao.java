public class RegraPosGraduacao implements RegraEmprestimo {
    @Override
    public int calcularPrazoEmprestimo() {
        return 5;
    }
    
    @Override
    public boolean validarEmprestimo() {
        return true;
    }
}
