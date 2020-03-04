/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
 class NameThread extends Thread{
    Socket socket;
    public  NameThread(Socket socket){
        this.socket = socket;
    }
    public void run(){
        // Tao hai luong nhap xuat
        try{
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            while(true){
                byte b_namein[] = new byte[100];
                int n = is.read(b_namein);
                String namein = new String(b_namein,0,n);
                // Dieu kien thoat
                if(namein.equals("q")) break;
                String name= tachTen(namein);
                os.write(name.getBytes());
            }
            socket.close();
            System.out.println("Dong socket"+socket.getPort());
        } catch(IOException e){
            System.out.println("Co loi khi noi ket 1 client");
        }
    }
    // truong thao
    private String tachTen(String name){
        // Loai bo khoang trang thua
        name = name.trim();
        // Lay vị tri khoang trang cuoi cung
        int index = name.lastIndexOf(" ");
        // Vi tri da chon den vi tri cuoi cung
        String kq = name.substring(index+1);
        return kq;
    } 
}
class NameServer  {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8998);
            System.out.println("Server dang khoi tao");
            while(true){
                try{
                    Socket clientSocket = serverSocket.accept();
                    NameThread nameThread = new NameThread(clientSocket);
                    nameThread.start();
                }catch(IOException e){
                    System.out.println("Co loi khi noi ket 1 client");
                }
            }
        }catch(IOException e){
            System.out.println("Server khong khoi tao duoc");
        }
    }
}
