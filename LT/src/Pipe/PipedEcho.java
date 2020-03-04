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
public class PipedEcho {

    
    public static void main(String[] args) {
        // TODO code application logic here
    try{
        PipedOutputStream cwPipe = new PipedOutputStream();
        PipedInputStream ciPipe = new PipedInputStream();
        PipedOutputStream swPipe = new PipedOutputStream(ciPipe);
        PipedInputStream siPipe = new PipedInputStream(cwPipe);
        PipedEchoServer server = new PipedEchoServer(siPipe, swPipe);
        PipedEchoClient client = new PipedEchoClient(ciPipe, cwPipe);
    }catch(IOException e){
        System.out.println("Pipe Ech error:"+e);
    }
    }  
}
