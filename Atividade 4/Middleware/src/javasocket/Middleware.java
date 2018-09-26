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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo user
 */
public class Middleware {
        
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(10000);
        Socket socket;
        
        try{
            
            while (true) {            
            
                TelaMiddleware tm = new TelaMiddleware();
                
                socket = serverSocket.accept();
                
                tm.dispose();
                tm.setVisible(true);
                tm.alterarCampo();
            
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            
                float valor1 = dataInputStream.readFloat();
                float valor2 = dataInputStream.readFloat();
                String operacao = dataInputStream.readUTF();
                
                tm.dispose();
                tm.setVisible(true);
                InetAddress ip = socket.getInetAddress();
                tm.alterarCampos(ip, valor1, valor2, operacao);
                
                if(operacao.equals("adicao")){
                    float res = new Middleware().adicao(valor1, valor2);
                    dataOutputStream.writeFloat(res);
                }
                
                if(operacao.equals("multiplicacao")){
                    float res = new Middleware().multiplicacao(valor1, valor2);
                    dataOutputStream.writeFloat(res);
                }
                                            
                socket.close();
                
            }
            
        } catch (EOFException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }
                
    }
    
    private float adicao(float v1, float v2) throws IOException{
        
        Socket socket = new Socket("192.168.0.7", 10500);
        
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeFloat(v1);
        dataOutputStream.writeFloat(v2);
        
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        float soma = dataInputStream.readFloat();
                
        return soma;
        
    }
    
    private float multiplicacao(float v1, float v2) throws IOException{
        
        Socket socket = new Socket("192.168.0.7", 10600);
        
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeFloat(v1);
        dataOutputStream.writeFloat(v2);
        
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        float mult = dataInputStream.readFloat();
                
        return mult;
        
    }
    
}
