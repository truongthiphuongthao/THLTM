/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author Dell
 */
public class NameClient {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Socket clientSocket = new Socket("127.0.0.1",8998);
            // Tao hai luong nhap xuat
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            Scanner kb = new Scanner(System.in);
            while(true){
                System.out.println("Nhap ho ten:");
                String hoten = kb.nextLine();
                if(hoten.equals("q")) break;
                byte[] hoten_in = hoten.getBytes();
                os.write(hoten_in);
                // Tao mang byte de nhan du lieu ve
                byte b_result[] = new byte[100];
                int n = is.read(b_result);
                String result = new String(b_result,0,n);
                System.out.println("Ket qua la:"+result);          
            }       
        }catch(IOException e){
            System.out.println("Loi noi ket "+e.toString());
        }
    }
    
}
