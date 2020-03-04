
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Dell
 */
public class TestServer {

    public static void main(String[] args) {
        // TODO code application logic here
        try{
            DatagramSocket ds = new DatagramSocket(7);
            System.out.println("Da khoi tao xong server");
            byte[] buffer = new byte[60000];
            while(true){
                DatagramPacket goinhan = new DatagramPacket(buffer, buffer.length);
                ds.receive(goinhan);
                String str = new String(goinhan.getData(),0,goinhan.getLength());
                DatagramPacket goigui = new DatagramPacket(str.getBytes(),goinhan.getLength(),goinhan.getAddress(),goinhan.getPort());
                ds.send(goigui);
            }
        }catch(IOException e){
            System.err.println(e);
        }
    }
    
}
