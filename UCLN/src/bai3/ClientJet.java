/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bai3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ClientJet {
    private final static int congTCP = 2610;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Socket tcpSocket = new Socket("127.0.0.1",congTCP);
            System.out.println("Client da noi ket voi server");
            InputStream is = tcpSocket.getInputStream();
            OutputStream os = tcpSocket.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os);
            Scanner kb = new Scanner(System.in);
            String send="b1609548 1604";
            pw.println(send);
            pw.flush();
            // Nhan ve chuoi
            String recv = sc.nextLine();
            System.out.println(recv);          
            // Cho biet co bao nhieu chuyen bay
            String sendinfo = "list";
            pw.println(sendinfo);
            pw.flush();
            // Hien thi thong tin chuyen bay
            String recvinfo = sc.nextLine();
            System.out.println("Thong tin chuyen bay");
            System.out.println(recvinfo);
            String[] params = recvinfo.split(";");
            String[] id;
            String[] soghe;
            ArrayList <String[]> flights = new ArrayList<>();
            int countghe = 0;
            // Tong so ghe trong
            for(int i=0;i<params.length;i++){
                soghe = params[i].split(",");
                flights.add(soghe);
                countghe+=Integer.parseInt(soghe[5]);
            }
            String ttmuaVe = "bought";
            pw.println(ttmuaVe);
            pw.flush();
            String infomuaVe =sc.nextLine();
            String paramsve[] = infomuaVe.split(";");
            if (infomuaVe.equals("")) paramsve = new String[0];
            
            ArrayList<String> bookedTicket = new ArrayList<> ();
            for (String ticket : paramsve) {
                bookedTicket.add(ticket);
            }
            
            if (paramsve.length>=3)
                System.out.println("Da mua 3 ve");
            else {
                System.out.println("Tong so ghe trong:"+countghe);
                for (int i = 0; i<flights.size(); ++i) {
                    if (Integer.parseInt(flights.get(i)[5]) >= 3){
                        for (int j=0;j<(3-paramsve.length);++j) {
                            String sendLoaiVe ="buy " +flights.get(i)[0];
                            pw.println(sendLoaiVe);
                            pw.flush();
                            String typeinfo = sc.nextLine();
//                            System.out.println(typeinfo);
                            bookedTicket.add(typeinfo);
                        }
                        break;
                    }
                }
            }
            for (String ticket: bookedTicket) {
                System.out.println ("Ve da dat: " + ticket);
            }
            String cmdCancel ="cancel " + bookedTicket.get(0);
            pw.println(cmdCancel);
            pw.flush();
//            String sendLoaiVe,typeinfo="";
//            // Dat ba ghe trong cung 1 chuyen bay
//            for(int i=0;i<params.length;i++){
//                if(countghe==0){
//                    System.out.println("Chuyen bay het ve");
//                    break;
//                } else {
//                    id = params[i].split(",");
//                    for(int j=0;j<3;j++){
//                        System.out.println(" Mua ve ---"+id[0]+" --- thu "+(j+1)+":");
//                        sendLoaiVe ="buy " +id[0];
//                        pw.println(sendLoaiVe);
//                        pw.flush();
//                        typeinfo = sc.nextLine();
//                        System.out.println(typeinfo);
//                        if(typeinfo.equals("Het ghe")){
//                            break;
//                        }
//                    }
//                }            
//            }
            // Huy 1 chuyen bay trong 3 dua
            
            
            tcpSocket.close();      
        }catch(IOException e){
            System.out.println(e.toString());
        }
    }
    
}
