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
public class cMother implements Mother {
    private String lastNauAn;
    
    public String getLastNauAn () {
        if (lastNauAn == null)
            return "";
        return lastNauAn;
    }
    public void nauAn(String c){
        System.out.println("Dang nau an: " + c);
        lastNauAn = c;
    }
}
