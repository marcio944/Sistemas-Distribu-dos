package servidor;

/**
 *
 * @author Casa
 */


public class Banco {
    
    Conexao conex = new Conexao();
    Pessoa pessoa = new Pessoa();
    
    public void salvar(Pessoa pessoa) throws Exception{
        
        conex.conexao();
        
        java.sql.PreparedStatement pst;
        pst = conex.con.prepareStatement("insert into dados(nome, cpf, imc) values "
                + "(?,?,?) ");
        pst.setString(1, pessoa.getNome());
        pst.setString(2, pessoa.getCpf());
        pst.setFloat(3, pessoa.getImc());
        
        pst.execute();
        
        conex.desconecta();
        
    }
    
}
