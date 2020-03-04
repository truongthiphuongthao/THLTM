/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ClientTalk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          System.out.println("Chat voi may server");
        // TODO code application logic here
        try{
            while(true){
                Scanner kb = new Scanner (System.in);
                DatagramSocket ds = new DatagramSocket();
                System.out.print(" May A:");
                String sendA = kb.nextLine();
                if(sendA.equals("q")) break;
                // Thong tin ve goi gui
                InetAddress dc = InetAddress.getByName("127.0.0.1");
                int cong = 8998;
                byte b_sendA[] = sendA.getBytes();
                int n_sendA = b_sendA.length;
                // Tao goi gui
                DatagramPacket goigui = new DatagramPacket(b_sendA, n_sendA,dc,cong);
                // Gui goi gui
                ds.send(goigui);
                // Chua goi nhan
                byte b_receiveB[] = new byte[60000];
                int n_receiveB = b_receiveB.length;
                // Tao goi nhan 
                DatagramPacket goinhan = new DatagramPacket(b_receiveB,n_receiveB);
                // Nhan goi nhan
                ds.receive(goinhan);
                // Lay thong tin goi nhan hien thi len man hinh
                byte b_result[] = goinhan.getData();
                int n_result = goinhan.getLength();
                String result = new String(b_result,0,n_result);
                System.out.println("May B:"+result);
                // Dong socket
                ds.close();
            }
        }catch(IOException e){
            System.out.println("Khong noi ket duoc voi server"+e.toString());
        }
        
    }
    
}
