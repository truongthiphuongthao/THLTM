/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bai3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Dell
 */
public class ClientMultiUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            while(true){
                InetAddress dcnhom = InetAddress.getByName("227.8.9.10");
                MulticastSocket ms = new MulticastSocket(2000);
                ms.joinGroup(dcnhom);
                // Tao goi nhan
                byte b_recv[] = new byte[640000];
                int n_recv = b_recv.length;
                DatagramPacket goinhan = new DatagramPacket(b_recv, n_recv);
                ms.receive(goinhan);
                // Hien thi goinhan
                String result = new String(goinhan.getData(),0,goinhan.getLength());
                System.out.println("Hien thi goi nhan:"+result);
                ms.leaveGroup(dcnhom);
                
            }
        }catch(IOException e){
            System.out.println("Khong noi ket den server"+e.toString());
        }
    }
    
}
