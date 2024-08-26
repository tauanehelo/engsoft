public class RegraGraduacao implements RegraEmprestimo {
    @Override
    public int prazoEmprestimo() {
        return 3;
    }
    @Override
    public int limiteEmprestimosEmAberto() {
        return 3;
    }

    @Override
    public boolean validarEmprestimo() {
        return true;
    }
}
