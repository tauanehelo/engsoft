import java.util.Date;

public class Exemplar {
    private final Livro livro;
    private final String codigoExemplar;
    private String status;
    private Usuario detentor;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Exemplar(Livro livro, String codigoExemplar) {
        this.livro = livro;
        this.codigoExemplar = codigoExemplar;
        this.status = "Disponivel";
        this.dataEmprestimo = null;
        this.dataDevolucao = null;
    }

    public void emprestar(Usuario usuario){
        this.status = "Emprestado";
        this.detentor = usuario;
        this.dataEmprestimo = new Date();
        this.dataDevolucao = null;
    }
    public void devolver(){
        this.status = "Disponivel";
        this.detentor = null;
        this.dataDevolucao = new Date();
        this.detentor = null;
    }
    
    public Usuario getDetentor() {
        return detentor;
    }

    public String getStatus() {
        return this.status;
    }

    public String getCodigoExemplar() {
        return this.codigoExemplar;
    }
    
    public Livro getLivro(){
        return this.livro;
    }

    public Date getDataEmprestimo() {
        return this.dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return this.dataDevolucao;
    }

    public boolean isEmprestado() {
        return this.status.equals("Emprestado");
    }
}
