/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplo.ejb;

import exemplo.modelo.Pessoa;
import javax.ejb.Remote;

/**
 *
 * @author cesar
 */
@Remote
public interface PessoaRemote {
    public Pessoa salvar(Pessoa p) throws Exception;
    
    public Pessoa consultarPorId(Long id);
    
    public void remover(Long id);
}
