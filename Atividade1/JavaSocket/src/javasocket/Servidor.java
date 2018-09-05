/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasocket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo user
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(10000);
        Socket socket;
        int i=0;
        float soma=0;
        float media;
        
        try{
            
            while (true) {            
            
                System.out.println("Esperando conexão...");
                                
                socket = serverSocket.accept();
                System.out.println("Conexão estabelecida com o cliente!");
            
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            
                Float valor = dataInputStream.readFloat();
                System.out.println("Valor " + valor + " recebido do cliente: " + socket.getInetAddress());
                
                soma = (soma + valor);
                
                i++;
                 
                System.out.println("Encerrando conexão!\n");
                
                if(i == 3){
                    media = (soma / 3);
                    System.out.println("\nMédia: " + media);
                    System.out.println("\n");
                    i = 0;
                    soma = 0;
                }
            
                socket.close();
                
            }
            
        } catch (EOFException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }
                
    }
    
}
