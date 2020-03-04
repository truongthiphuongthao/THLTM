/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dethi;

/**
 *
 * @author Dell
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Child c = new Child();
        System.out.print (c instanceof Father);
        c.nauAn("Nau em");
        c.suaDien(1);
    }
    
}
