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
public class Child  implements Mother{
    cFather father;
    cMother mother;
    public void suaDien(int c){
        father.suaDien(c);
    }
    public void nauAn(String s){
        mother.nauAn(s);
    }
    public Child(){
        father = new cFather();
        mother = new cMother();
    }
    
}
