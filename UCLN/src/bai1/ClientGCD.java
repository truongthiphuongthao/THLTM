/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bai1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ClientGCD {

    /**
     * @param args the command line arguments
     */
    private static int UCLN(int a, int b){
        int result;
        while(a!=b){
            if(a>b){
                a = a-b;
            }
            else{
                b=b-a;
            }
        }
        return result=a;
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        // Giao thức TCP
        try{
            // Tao noi ket
                Socket clientSocket = new Socket("127.0.0.1",2610);
                System.out.println("Client da ket noi");
                  // Tao hai luong nhap xuat
                InputStream is = clientSocket.getInputStream();
                OutputStream os = clientSocket.getOutputStream();
                Scanner sc = new Scanner(is);
                Scanner kb = new Scanner(System.in);
                PrintWriter pw = new PrintWriter(os);
                System.out.print("");
                String mssv =kb.nextLine();
                String send =mssv;
                pw.println(send);
                pw.flush();
                byte ketquaxn[] = new byte[100];
                int n_ketquaxn = is.read(ketquaxn);
                String xacnhan = new String(ketquaxn,0,n_ketquaxn);
                System.out.println(""+xacnhan);
                
            while(true){
//                System.out.println("GET?");
                String yc ="GET";
                pw.println(yc);
                pw.flush();
                // Hien thi hai so A va B
                byte b_so[] = new byte[60000];
                int n_so = is.read(b_so);
                String so = new String(b_so,0,n_so);
                // tach so
                String[] params = so.split(" ");
                String chuoia =  params[0];
                String chuoib =  params[1];
                System.out.print("A:"+chuoia+" B:"+chuoib+"\n");
                int  a = Integer.parseInt(chuoia);
                int b = Integer.parseInt(chuoib);
                int ketquacuoi =UCLN(a,b);
                String send_ketquacuoi = "ANSWER "+ketquacuoi+"";
                System.out.println("Dap an:"+ketquacuoi);
                pw.println(send_ketquacuoi);
                pw.flush();
                // Hien thi tt danh gia
                byte b_danhgia[]= new byte[100];
                int n_danhgia = is.read(b_danhgia);
                String danhgia = new String(b_danhgia,0,n_danhgia);
                System.out.println("Ket qua danh gia:\n"+danhgia);
                
                }
        }catch(IOException e){
            System.out.println("Khong the noi ket den server"+e.toString());
        }
    }
    
}
