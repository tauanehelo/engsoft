import java.util.Date;

public class Reserva {
    private final Livro livro;
    private final Usuario usuario;
    private final Date dataReserva;

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
