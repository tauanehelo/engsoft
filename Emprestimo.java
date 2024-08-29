import java.util.Calendar;
import java.util.Date;

public class Emprestimo {
    private final Livro livro;
    private final Usuario usuario;
    private String status;
    private final Date dataEmprestimo;
    private final Date dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario){
        this.livro = livro;
        this.usuario = usuario;
        this.status = "Em curso";
        this.dataEmprestimo = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataEmprestimo);
        calendar.add(Calendar.DAY_OF_YEAR, usuario.getRegraEmprestimo().prazoEmprestimo());
        this.dataDevolucao = calendar.getTime();
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public Livro getLivro() {
        return livro;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    public Date getDataDevolucao() {
        return dataDevolucao;
    }
    
}