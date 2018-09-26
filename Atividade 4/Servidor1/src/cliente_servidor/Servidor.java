/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultStyledDocument;

/**
 *
 * @author lenovo user
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(10500);
        Socket socket;
        int i=0;
        float soma;
        float media;
        
        try{
            
            while (true) {            
                
                TelaServidor1 ts = new TelaServidor1();
                                                
                socket = serverSocket.accept();
            
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            
                float valor1 = dataInputStream.readFloat();
                
                float valor2 = dataInputStream.readFloat();
                
                soma = (valor1 + valor2);
                
                ts.dispose();
                ts.setVisible(true);
                ts.alteraCampo();
                ts.alteraCampos(valor1, valor2);
                
                dataOutputStream.writeFloat(soma);
                                             
                socket.close();
                
            }
            
        } catch (EOFException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }

        
    }
    
}
