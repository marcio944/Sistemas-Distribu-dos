package comum;


import cliente.TelaCliente;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import servidor.Banco;
import servidor.Pessoa;
import servidor.TelaServidor;

public class IMCImpl extends UnicastRemoteObject implements IMC {
    private static String SERVIDOR = "192.168.0.10/ServicoMensagens";
    private final static Integer PORTA = 8000;
    private static String SERVICO = "ServicoMensagens";
    
    private String n;
    private String c;
    private float msgRecebida = 0;
    private String ip;
    
    public static String getURI(){
        String uri = String.format("rmi://%s:%d/%s", SERVIDOR, PORTA, SERVICO);
        return uri;
    }

    public IMCImpl() throws RemoteException {
        super();
    }
    
    public float calcularIMC(float p, float a) throws RemoteException{
        return p/(a*a);
    }
    
    public void setDados(String nome, String cpf, float mensagem) throws RemoteException {
        n = nome;
        c = cpf;
        msgRecebida = mensagem;
    }
    
    public float getDados() throws RemoteException {
        return msgRecebida;
    }

    public void salvarDados() throws RemoteException {
        Pessoa p = new Pessoa();
        Banco b = new Banco();
        try {
            ip = getClientHost();
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(IMCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setNome(n);
        p.setCpf(c);
        p.setImc(msgRecebida);
        TelaServidor ts = new TelaServidor();
        ts.dispose();
        ts.setVisible(true);
        ts.setCampos(ip, p);
        try {
            b.salvar(p);
            JOptionPane.showMessageDialog(null, "Dados armazenados com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar");
        }
    }
    
}
