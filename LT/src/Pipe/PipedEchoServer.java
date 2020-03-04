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
public class PipedEchoServer extends Thread {
    PipedInputStream readPipe;
    PipedOutputStream writePipe;
    PipedEchoServer( PipedInputStream readPipe, PipedOutputStream writePipe){
        this.readPipe = readPipe;
        this.writePipe = writePipe;
        System.out.println("Server is starting");
        start();
    }
    public void run(){
        while(true){
            try{
                int ch = readPipe.read();
                ch = Character.toUpperCase((char)ch);
                writePipe.write(ch);
            }catch(IOException e){
                System.out.println("Echo server error:"+e);
            }
        }
    }
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
    
}
