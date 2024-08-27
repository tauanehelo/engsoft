import java.util.Date;

public class Reserva {
    private Livro livro;
    private Usuario usuario;
    private Date dataReserva;

    public Reserva(Livro livro, Usuario usuario){
        this.livro = livro;
        this.usuario = usuario;
        this.dataReserva = new Date();
    }
    
    public Livro getLivro() {
        return livro;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Date getDataReserva() {
        return dataReserva;
    }
}
