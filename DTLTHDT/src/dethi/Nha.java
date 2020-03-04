/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Nha {
    private String diachi;
    private String chunha;
    private String cmnd;
    private float dtdat;
    private float dtsd;
    private long gia;
    // Ham xay dung mac nhien
    public Nha(){
       diachi = "";
       chunha = "";
       cmnd = "";
       dtdat = 0;
       dtsd = 0;
       gia =0;
    }
    // Ham xay dung co tham so
    public Nha (String diachi, String chunha, String cmnd, float dtdat, float dtsd, long gia){
        this.diachi = diachi;
        this.chunha = chunha;
        this.cmnd = cmnd;
        this.dtdat = dtdat;
        this.dtsd = dtsd;
        this.gia = gia;
    }
    // Ham xay dung sao chep
    public Nha(Nha nha){
        diachi = nha.diachi;
        chunha = nha.chunha;
        cmnd = nha.cmnd;
        dtdat = nha.dtdat;
        dtsd = nha.dtsd;
        gia = nha.gia;
    }
    // Ham sao chep xau
    public void makeCopy(Nha nha){
        diachi = nha.diachi;
        chunha = nha.chunha;
        cmnd = nha.cmnd;
        dtdat = nha.dtdat;
        dtsd = nha.dtsd;
        gia = nha.gia;
    }
    // Ham nhap thong tin tu ban phim
    public void nhapThongTin(){
        Scanner kb = new Scanner (System.in);
        System.out.print("Dia chi nha: ");
        diachi = kb.nextLine();
        System.out.print("Chu nha: ");
        chunha = kb.nextLine();
        System.out.print("CMND: ");
        cmnd = kb.nextLine();
        System.out.print("Dien tich dat: ");
        dtdat = kb.nextFloat();
        System.out.print("Dien tich su dung: ");
        dtsd = kb.nextFloat();
        System.out.print("Gia: ");
        gia = kb.nextLong();
    }
    // Ham hien thi thong tin ra man hinh
    public void hienThi(){
        System.out.println("Dia chi nha: "+diachi);
        System.out.println("Chu nha : "+chunha);
        System.out.println("CMND : "+cmnd);
        System.out.println("Dien tich dat: "+dtdat);
        System.out.println("Dien tich su dung: "+dtsd);
        System.out.println("Gia: "+gia);
        
    }
    // Lay ra so CMND cua chu nha
    public String layCMND(){
        return this.cmnd;
    }
    // Lay ra tri gia cua can nha
    public long layGia(){
        return this.gia;
    }
    // Tinh ra don gia su dung cua 1 don vi dien tich su dung
    public float donGiaSD(){
        return this.gia * this.dtsd;
    }
    
}
