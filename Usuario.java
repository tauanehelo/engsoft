import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public abstract class Usuario {
    protected String codigoUsuario;
    protected String nome;
    protected RegraEmprestimo regraEmprestimo;
    protected List<Emprestimo> emprestimosFeitos;
    protected List<Reserva> reservasFeitas;
    
    public Usuario(String codigoUsuario, String nome) {
        this.codigoUsuario = codigoUsuario;
        this.nome = nome;
        this.emprestimosFeitos = new ArrayList<Emprestimo>();
        this.reservasFeitas = new ArrayList<Reserva>();
    };

    public void guardarEmprestimo(Emprestimo emprestimo){
        this.emprestimosFeitos.add(emprestimo);
    }
    public void guardarReserva(Reserva reserva) {
        this.reservasFeitas.add(reserva);
    }

    public void devolverEmprestimo(Livro livro){
        Emprestimo emprestimo = null;
        for (Emprestimo emprestimoFeito : emprestimosFeitos) {
            if(livro.equals(emprestimoFeito.getLivro())){
                emprestimo = emprestimoFeito;
            }
        }
        int index = this.emprestimosFeitos.indexOf(emprestimo);
        Emprestimo emprestimoDevolvido = this.emprestimosFeitos.get(index);
        emprestimoDevolvido.setStatus("Finalizado");
    }

    public boolean isDevedor(){
        Date hoje = new Date();
        for (Emprestimo emprestimoFeito : emprestimosFeitos) {
            if(emprestimoFeito.getStatus().equals("Em curso") && hoje.after(emprestimoFeito.getDataDevolucao())){
                return true;
            }
        }
        return false;
    }

    public boolean podePegarEmprestimo(){
        int emprestimosEmAberto = 0;
        for (Emprestimo emprestimoFeito : emprestimosFeitos) {
            if(emprestimoFeito.getStatus().equals("Em curso")){
                emprestimosEmAberto++;
            }
        }
        return emprestimosEmAberto < regraEmprestimo.limiteEmprestimosEmAberto();
    }
    public Date consultaDataEmprestimo(Livro livro){
        Emprestimo emprestimo = null;
        for (Emprestimo emprestimoFeito : emprestimosFeitos) {
            if(livro.equals(emprestimoFeito.getLivro())){
                emprestimo = emprestimoFeito;
            }
        }
        int index = this.emprestimosFeitos.indexOf(emprestimo);
        Emprestimo emprestimoConsulta = this.emprestimosFeitos.get(index);
        return emprestimoConsulta.getDataEmprestimo();
    }
    public Date consultaDataDevolucao(Livro livro){
        Emprestimo emprestimo = null;
        for (Emprestimo emprestimoFeito : emprestimosFeitos) {
            if(livro.equals(emprestimoFeito.getLivro())){
                emprestimo = emprestimoFeito;
            }
        }
        int index = this.emprestimosFeitos.indexOf(emprestimo);
        Emprestimo emprestimoConsulta = this.emprestimosFeitos.get(index);
        return emprestimoConsulta.getDataDevolucao();
    }
    public boolean hasEmprestimoAberto(Livro livro){
        for (Emprestimo emprestimoFeito : emprestimosFeitos) {
            if(livro.equals(emprestimoFeito.getLivro()) && emprestimoFeito.getStatus().equals("Em curso")){
                return true;
            }
        }
        return false;
    }

    String getCodigoUsuario(){
        return this.codigoUsuario;
    };
    String getNome(){
        return this.nome;
    };
    RegraEmprestimo getRegraEmprestimo(){
        return this.regraEmprestimo;
    };
    public List<Emprestimo> getEmprestimosFeitos() {
        return emprestimosFeitos;
    };
    public List<Reserva> getReservasFeitas() {
        return reservasFeitas;
    };
    
}
