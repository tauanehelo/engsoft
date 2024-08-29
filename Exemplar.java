public class Exemplar {
    private final Livro livro;
    private final String codigoExemplar;
    private String status;
    private Usuario detentor;


    public Exemplar(Livro livro, String codigoExemplar) {
        this.livro = livro;
        this.codigoExemplar = codigoExemplar;
        this.status = "Disponivel";
    }

    public void emprestar(Usuario usuario){
        this.status = "Emprestado";
        this.detentor = usuario;
    }
    public void devolver(){
        this.status = "Disponivel";
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

    public boolean isEmprestado() {
        return this.status.equals("Emprestado");
    }
}
