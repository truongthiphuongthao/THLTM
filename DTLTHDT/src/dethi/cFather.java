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
public class cFather implements Father{
    
    int lastSuaDien;
    public int getLastSuaDien (){
        return lastSuaDien;
    }
    public void suaDien(int c){
        System.out.println("Dang sua dien: So " + c);
    }
    
}
