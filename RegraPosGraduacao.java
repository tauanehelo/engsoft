public class RegraPosGraduacao implements RegraEmprestimo {
    @Override
    public int prazoEmprestimo() {
        return 5;
    }
    @Override
    public int limiteEmprestimosEmAberto() {
        return 4;
    }
    
    @Override
    public boolean validarEmprestimo() {
        return true;
    }
}
