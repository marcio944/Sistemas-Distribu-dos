
package servidor;

import comum.IMCImpl;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import comum.IMC;

public class Servidor {

    public Servidor() throws RemoteException, MalformedURLException {
        try{
            Registry registry = LocateRegistry.createRegistry(8000);
            registry.bind("ServicoMensagens", new IMCImpl());
        } catch (Exception e){
            System.out.println("Erro: "+e);
        }
    }
 
    public static void main(String[] args){
        try {
            new Servidor();
            System.out.println("Servidor rodando...");
        } catch (Exception e) {
            System.out.println("Erro: "+e);        
        }
    }
    
    
}
