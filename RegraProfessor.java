public class RegraProfessor implements RegraEmprestimo {
    @Override
    public int prazoEmprestimo() {
        return 7;
    }

    @Override
    public int limiteEmprestimosEmAberto() {
        return 0;
    }
    
    @Override
    public boolean validarEmprestimo() {
        return true;
    }
}
