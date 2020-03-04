/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bai3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Dell
 */
public class ServerMultiUDP {
//    private static byte[] readChunk(FileInputStream file, int fileSize){
//        byte result[] = new byte[fileSize];
//        try{
//            int sizeFile = file.read(result);
//            if(sizeFile==-1){
//                return null;
//            }
//        }catch(IOException e){
//            System.out.println(e.toString());
//        }
//        return result;
//    }
    private static byte[] readChunk(FileInputStream file, int fileSize){
        byte result[] = new byte[fileSize];
        try{
            int sizeFile = file.read(result);
            if(sizeFile ==-1){
                return null;
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }
        return result;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            // Tao socket
            DatagramSocket ds = new DatagramSocket(2001);
            File file = new File ("F:\\test\\vidu.txt");
            FileInputStream stream = new FileInputStream(file);
            byte[] bytes = readChunk (stream, (int) file.length());
            while (true) {
                InetAddress dc = InetAddress.getByName("227.8.9.10");
                int cong = 2000;
                if (bytes == null)
                    break;
                int n_bytes = bytes.length;
                DatagramPacket goigui = new DatagramPacket(bytes,n_bytes,dc,cong);
                ds.send(goigui);
                System.out.println ("Sleeping for 30s");
                Thread.sleep(30000);
            }
        } catch(IOException e){
                System.out.println("Khong khoi tao duoc server"+e.toString());
        } catch (Exception e) {
            
        }
    }
}
