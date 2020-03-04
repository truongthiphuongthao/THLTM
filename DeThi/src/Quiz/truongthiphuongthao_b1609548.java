/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Quiz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class truongthiphuongthao_b1609548 {
      private final static int congUDP = 3333;
      private final static int congTCP = 4444;
      private final static String dcserver ="127.0.0.1";
      
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            DatagramSocket udpSocket = new DatagramSocket();
            Socket tcpSocket = new Socket(dcserver,congTCP);
            InputStream is = tcpSocket.getInputStream();
            OutputStream os = tcpSocket.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os);
            Scanner kb = new Scanner(System.in);
            System.out.println("Username:");
            String username = kb.nextLine();
            System.out.println("Password:");
            String password = kb.nextLine();
            String send = username + " " +password;
            InetAddress dc = InetAddress.getByName(dcserver);
            DatagramPacket goigui = new DatagramPacket(send.getBytes(),send.length(),dc,congUDP);
            udpSocket.send(goigui);
            
            byte b_recv[] = new byte[60000];
            int n_recv = b_recv.length;
            DatagramPacket goinhan = new DatagramPacket(b_recv,0,n_recv);
            udpSocket.receive(goinhan);
            
            String recv = new String(goinhan.getData(),0,goinhan.getLength());
            pw.println(recv);
            pw.flush();
            
            
            for(int i=0;i<5;i++){
                String question = sc.nextLine();
                System.out.println(question);
                for(int j=0;j<4;j++){
                     String answer = sc.nextLine();
                    System.out.println(answer);
                }
                System.out.println("Dap an ban la:");
                String solution = kb.nextLine();
                pw.println(solution);
                pw.flush();
            }
            String result = sc.nextLine();
            System.out.println(result);
            tcpSocket.close();
            udpSocket.close();
            
        }catch(IOException e){
            System.out.println(e.toString());
        }
    }
    
}
