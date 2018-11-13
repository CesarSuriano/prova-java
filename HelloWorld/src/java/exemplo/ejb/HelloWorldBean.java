package exemplo.ejb;


import javax.ejb.Remote;
import javax.ejb.Stateless;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cesar
 */
@Stateless(mappedName = "Bean1")
public class HelloWorldBean implements HelloWorldRemote{

    @Override
    public String olaMundo() {
        return "Olá mundo com EJB!!";
    }
    
}
