/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class mainNha {
     
    public static void main(String[] args) {
        // TODO code application logic here
        // Tao ra 1 doi tuong tong quat n1
//        Nha n1  = new Nha();
//        // Nhap thong tin cho n1
//        n1.nhapThongTin();
//        // Sao chep gia tri nha n1 cho doi tuong nha n2
//        Nha n2 = new Nha();
//        n2.makeCopy(n1);
//        System.out.println("Thong tin doi tuong n2");
//        n2.hienThi();
        Scanner kb = new Scanner(System.in);
        System.out.println("Nhap so can nha: ");
        int n = kb.nextInt();
       
        ArrayList<Nha> arrNha = new ArrayList<Nha>();
        System.out.println("Nhap danh sach n can nha:");
        for(int i=0;i<n;i++){
            System.out.println("Can nha thu "+(i+1)+":");
            Nha nha = new Nha();
            nha.nhapThongTin();
            arrNha.add(nha);
            
        }
        for(int i=0;i<arrNha.size();i++){
            System.out.println("Thong tin can nha thu "+(i+1)+":");
            arrNha.get(i).hienThi();
        }
       // Tim can nha co gia tri cao nhat trong danh sach
        int index = 0;
        long max = arrNha.get(index).layGia();
        
        System.out.println("Can nha co gia tri cao nhat trong danh sach");
        for(int i=1;i<arrNha.size();i++){
           if(arrNha.get(i).layGia() >= max ){
               index = i;
               max = arrNha.get(index).layGia();
           }
        }
        for(int i=0;i<arrNha.size();i++){
            if(arrNha.get(i).layGia()==max){
                arrNha.get(i).hienThi();
            }
        }
         
      //  13 12
        //temp = 12;
        // i = 12
        // j = 13
        
        // Hien thi danh sach nha duoc sap xep theo don gia su dung tang dan
        for(int i=0;i<arrNha.size()-1;i++){
            for(int j=i+1;j<arrNha.size();j++){
                if(arrNha.get(j).donGiaSD() < arrNha.get(i).donGiaSD()){
                  Nha temp = new Nha();
                  temp.makeCopy(arrNha.get(j));
                  arrNha.get(i).makeCopy(arrNha.get(j));
                  arrNha.get(j).makeCopy(temp);
                  
                }
            }
        }
         for(int i=0;i<arrNha.size();i++){
             System.out.println("Thong tin can nha da sap xep thu "+(i+1)+":");
             arrNha.get(i).hienThi();
        }
        
    }
    
}
