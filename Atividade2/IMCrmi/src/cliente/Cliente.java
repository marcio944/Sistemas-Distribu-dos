
package cliente;

import comum.IMCImpl;
import java.rmi.Naming;
import java.util.Scanner;
import comum.IMC;
import javax.swing.JOptionPane;
import servidor.TelaServidor;

public class Cliente {
    
    public void executar(String nome, String cpf, float p, float a){
        
        try{
            IMC imc = (IMC) Naming.lookup("rmi://192.168.0.10:8000/ServicoMensagens");
                float i = imc.calcularIMC(p, a);
                imc.setDados(nome,cpf,i);
                JOptionPane.showMessageDialog(null, "IMC: " + imc.getDados());
                imc.salvarDados();
        } catch (Exception e) {
            System.out.println("Erro: "+e);
        }
        
        
    }
    
    public static void main(String[] args) {
        
    }
    
}
