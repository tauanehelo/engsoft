public class Reserva {
    private Livro livro;
    private Usuario usuario;

    public Reserva(Livro livro, Usuario usuario){
        this.livro = livro;
        this.usuario = usuario;
    }
    
    public Livro getLivro() {
        return livro;
    }
    public Usuario getUsuario() {
        return usuario;
    }
}
