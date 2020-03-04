import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
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
public class ServerPassUser{
      private final static int congUDP = 3333;
      private final static int congTCP = 4444;
      private final static String dcserver ="127.0.0.1";
      
      public static void main(String[] args){
          try{
              // Noi ket UDP, TCP
              DatagramSocket udpSocket = new DatagramSocket(congUDP);
              ServerSocket tcpSocket = new ServerSocket(congTCP);
              System.out.println("Server dang khoi tao");
              while(true){
                  Socket tcpClient = tcpSocket.accept();
                  System.out.println("Co 1 client noi ket:"+tcpClient.getPort());
                  QuizThread quizThread = new QuizThread(tcpClient, udpSocket);
                  quizThread.start();
              }
              
          }catch(IOException e){
              System.out.println(e.toString());
          }
      }
//      public static void main(String[] args){
//          ServerPassUser spu = new ServerPassUser ();
//          spu.mymain();
//      }
//      
//      public void mymain () {
//          try{
//              // Noi ket UDP, TCP
//              DatagramSocket udpSocket = new DatagramSocket(congUDP);
//              ServerSocket tcpSocket = new ServerSocket(congTCP);
//              System.out.println("Server dang khoi tao");
//              while(true){
//                  Socket tcpClient = tcpSocket.accept();
//                  System.out.println("Co 1 client noi ket:"+tcpClient.getPort());
//                  QuizThread quizThread = new QuizThread(tcpClient, udpSocket);
//                  quizThread.start();
//              }
//              
//          }catch(IOException e){
//              System.out.println(e.toString());
//          }
//      }
      public static class Question{
        String question;
        String answer[] = new String[4];
        String solution;
        public Question(String question, String a, String b, String c, String d, String solution){
            this.question = question;
            answer[0] ="A: "+ a;
            answer[1] ="B: "+ b;
            answer[2] ="C: "+ c;
            answer[3] ="D: "+ d;
            this.solution = solution;
        }
    }

      public static class QuizThread extends Thread{
          Socket tcpSocket;
          DatagramSocket udpSocket;
          public QuizThread(Socket tcpSocket,DatagramSocket udpSocket){
              this.tcpSocket = tcpSocket;
              this.udpSocket = udpSocket;
          }
          @Override
          public void run(){
              try{
                  // Question
                    Question questionList[] = new Question[5];
                    questionList[0]=new Question("1+2=?","3","2","1","6","A");
                    questionList[1]=new Question("1+1=?","3","2","1","6","B");
                    questionList[2]=new Question("1+0=?","3","2","1","6","C");
                    questionList[3]=new Question("1+9=?","3","2","1","10","D");
                    questionList[4]=new Question("1+7=?","3","2","1","8","D");
                    InputStream is = tcpSocket.getInputStream();
                    OutputStream os = tcpSocket.getOutputStream();
                    Scanner sc = new Scanner(is);
                    PrintWriter pw = new PrintWriter(os);
                    Scanner kb = new Scanner(System.in);
                    // Nhan thong tin password , username ma Client gui ve
                    byte b_playerinfo[] = new byte[60000];
                    int n_playerinfo = b_playerinfo.length;
                    DatagramPacket goinhan = new DatagramPacket(b_playerinfo,0,n_playerinfo);
                    udpSocket.receive(goinhan);
                    // Gui cho client mot password bat ki
                    String passwd ="pthao2610";
                    DatagramPacket goigui = new DatagramPacket(passwd.getBytes(),passwd.length(),goinhan.getAddress(),goinhan.getPort());
                    udpSocket.send(goigui);
                    // Nhan lai password va kiem tra
                    String passwdgame = sc.nextLine();
                    int count =0;
                    if(passwdgame.equals(passwd)){
                        for(int i=0;i<5;i++){
                            String question = "Cau hoi "+(i+1)+":"+questionList[i].question;
                            pw.println(question);
                            pw.flush();
                            for(int j=0;j<4;j++){
                                String answer = questionList[i].answer[j];
                                pw.println(answer);
                                pw.flush();
                            }
                            String solution = sc.nextLine();
                            if(solution.equals(questionList[i].solution)){
                                count ++;
                            }
                        }
                    }
                        String result ="Ban da tra loi duoc:"+count+"/ 5 cau";
                        pw.println(result);
                        pw.flush();
                  System.out.println("Client o port"+tcpSocket.getPort()+"da hoan thanh bai kiem tra");
              }catch(IOException e){
                  System.out.println(e.toString());
              }
              catch(NoSuchElementException e){
                  System.out.println(e.toString());
              }
          }
      }
  }