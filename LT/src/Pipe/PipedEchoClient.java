/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 *
 * @author Dell
 */
public class PipedEchoClient extends Thread {
    PipedInputStream readPipe;
    PipedOutputStream writePipe;
    PipedEchoClient( PipedInputStream readPipe, PipedOutputStream writePipe){
        this.readPipe = readPipe;
        this.writePipe = writePipe;
        System.out.println("Client creation");
        start();
    }
    public void run(){
        while(true){
            try{
                int ch = System.in.read();
                writePipe.write(ch);
                ch = readPipe.read();
                System.out.print((char)ch);
            }catch(IOException e){
                System.out.println("Echo client error:"+e);
            }
        }
    }
//    public static void main(String[] args) {
//        // TODO code application logic here
//        
//    }
    
}
