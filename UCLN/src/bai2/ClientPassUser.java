
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bai2;



/**
 *
 * @author Dell
 */
public class ClientPassUser {
    private final static int congTCP = 4444;
    private final static int congUDP = 3333;
    private final static String dcserver = "127.0.0.1";
    public static void main(String[] args) {
        try{
            // Ket noi UDP, TCP
            DatagramSocket udpSocket = new DatagramSocket();
            Socket tcpSocket = new Socket(dcserver,congTCP);
            InputStream is = tcpSocket.getInputStream();
            OutputStream os = tcpSocket.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os);
            Scanner kb = new Scanner(System.in);
            System.out.println("Username:");
            String username = kb.nextLine();
            System.out.println("Password");
            String password = kb.nextLine();
            String request = username+" "+password;
            // Su dung UDP gui username và password
            InetAddress dc = InetAddress.getByName(dcserver);
            DatagramPacket goigui = new DatagramPacket(request.getBytes(),request.length(),dc,congUDP);
            // Gui goi gui
            udpSocket.send(goigui);
            // Server gui cho Client mot chuoi Password tro choi
            byte b_passwdgame[] = new byte[60000];
            int n_passwdgame = b_passwdgame.length;
            DatagramPacket goinhan = new DatagramPacket(b_passwdgame,0,n_passwdgame);
            udpSocket.receive(goinhan);
            // Gui password tro choi cho server
            String passwdgame = new String(goinhan.getData(),0,goinhan.getLength());
            pw.println(passwdgame);
            pw.flush();
            // TCP server gui cho ban 5 cau hoi
            for(int i=0;i<5;i++){
                String question = sc.nextLine();
                System.out.println(question);
                for(int j=0;j<4;j++){
                    String answer = sc.nextLine();
                    System.out.println(answer);
                }
                 // Tra loi dap an
                System.out.println("Dap an la:");
                String solution = kb.nextLine();
                // gui dap an cho server
                pw.println(solution);
                pw.flush();
            }         
            // Nhan ket qua dap tu server tra ve
            String result = sc.nextLine();
            System.out.println(result);
            // Dong socket
            udpSocket.close();
            tcpSocket.close();
            
        }catch(IOException e){
            System.out.println(e.toString());
        }
       
    }
}
