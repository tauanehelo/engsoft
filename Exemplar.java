public class Exemplar {
    private Livro livro;
    private String codigoExemplar;
    private String status;

    public Exemplar(Livro livro, String codigoExemplar, String status) {
        this.livro = livro;
        this.codigoExemplar = codigoExemplar;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getCodigoExemplar() {
        return codigoExemplar;
    }
}
