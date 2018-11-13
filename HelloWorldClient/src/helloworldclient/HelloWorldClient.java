/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworldclient;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import exemplo.ejb.HelloWorldRemote;

/**
 *
 * @author cesar
 */
public class HelloWorldClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        InitialContext ctx = new InitialContext();
        
        HelloWorldRemote ejb = (HelloWorldRemote) ctx.lookup("Bean1");
        
        System.out.println(ejb.olaMundo()); 
    }
    
}
