
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Dell
 */
public class TestClient {
    public final static int serverPort = 7;
    public static void main(String[] args) {
        try{
            if(args.length==0){
                System.out.println("Syntax: java UDPClient HostName"); return;
            }
            DatagramSocket ds = new DatagramSocket();
            InetAddress server  = InetAddress.getByName(args[0]);
            while(true){
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String theString = br.readLine();
                byte[] data = theString.getBytes();
                DatagramPacket dp = new DatagramPacket(data,data.length,server,serverPort);
                ds.send(dp);
                byte[] buffer = new byte[6000];
                DatagramPacket goinhan = new DatagramPacket(buffer,buffer.length);
                ds.receive(goinhan);
                System.out.println(new String(goinhan.getData(),0,goinhan.getLength()));
            }
        }catch(IOException e){
            System.err.println(e);
        }
        
    }
    
}
