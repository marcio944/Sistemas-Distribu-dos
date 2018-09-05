package comum;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.TelaServidor;

public interface IMC extends Remote {
    public float calcularIMC(float p, float a) throws RemoteException;
    public void setDados(String nome, String cpf, float mensagem) throws RemoteException; //método para enviar mensagens
    public float getDados() throws RemoteException; //método para ler mensagens
    public void salvarDados() throws RemoteException;
} 
