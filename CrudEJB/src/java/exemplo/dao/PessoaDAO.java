/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplo.dao;

import exemplo.modelo.Pessoa;
import javax.persistence.EntityManager;

/**
 *
 * @author cesar
 */
public class PessoaDAO {

    private EntityManager em;

    public PessoaDAO(EntityManager em) {
        this.em = em;
    }

    public Pessoa salvar(Pessoa p) throws Exception {
        if (p.getId() == null) {
            em.persist(p);
        } else {
            if (!em.contains(p)) {
                if (em.find(Pessoa.class, p.getId()) == null) {
                    throw new Exception("Erro ao atualizar os dados da Pessoa!!!");
                }
            }
            p = em.merge(p);
        }
        return p;
    }

    public Pessoa consultarPorId(Long id) {
        return em.find(Pessoa.class, id);
    }
    
    public void remover(Long id) {
        Pessoa p = consultarPorId(id);
        em.remove(p);
    }

}
