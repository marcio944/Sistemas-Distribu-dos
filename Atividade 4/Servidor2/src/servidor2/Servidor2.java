/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo user
 */
public class Servidor2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(10600);
        Socket socket;
        float mult;
        
        try{
            
            while (true) {            
                
                TelaServidor2 ts = new TelaServidor2();
                                                
                socket = serverSocket.accept();
            
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            
                float valor1 = dataInputStream.readFloat();
                
                float valor2 = dataInputStream.readFloat();
                
                mult = (valor1 * valor2);
                
                ts.dispose();
                ts.setVisible(true);
                ts.alteraCampo();
                ts.alteraCampos(valor1, valor2);
                
                dataOutputStream.writeFloat(mult);
                                             
                socket.close();
                
            }
            
        } catch (EOFException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }
        
    }
    
}
