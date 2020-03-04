/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class serverDocSo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Tao noi ket socket
        try{
            ServerSocket serverSocket = new ServerSocket(7);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Server dang duoc khoi tao");
            System.out.println("Co 1 client dang ket noi:"+clientSocket.getInetAddress()+"Cong:"+clientSocket.getPort());
            // Tao hai stream nhap xuat
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            // Doc 1 ki tu tu client gui qua
            while(true){
                int ch = is.read();
                String kq = "";
                // Dieu kien dung
                if(ch=='q') break;
                String[] results = new String[] {"khong", "mot", "hai", "ba", "bon", "nam", "sau", "bay", "tam", "chin"};
                if ('0' <= ch && ch <= '9') {
                    kq = results[ch-'0'];
                }
                else{
                    kq="khong biet";
                }
                byte b_result[] = kq.getBytes();
                os.write(b_result);
            }
            // Dong socket client
            clientSocket.close();
        } catch(IOException e){
            System.out.println("Loi noi ket tu server"+e.toString());
        }
    }
    
}
