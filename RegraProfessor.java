public class RegraProfessor implements RegraEmprestimo {
    @Override
    public int calcularPrazoEmprestimo() {
        return 7;
    }
    
    @Override
    public boolean validarEmprestimo() {
        return true;
    }
}
