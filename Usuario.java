import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public abstract class Usuario {
    protected String codigoUsuario;
    protected String nome;
    protected RegraEmprestimo regraEmprestimo;
    protected List<Date> datasDeDevolucao;
    
    public Usuario(String codigoUsuario, String nome) {
        this.codigoUsuario = codigoUsuario;
        this.nome = nome;
        this.datasDeDevolucao = new ArrayList<Date>();
    };

    String getCodigoUsuario(){
        return this.codigoUsuario;
    };
    String getNome(){
        return this.nome;
    };
    RegraEmprestimo getRegraEmprestimo(){
        return this.regraEmprestimo;
    };
}
