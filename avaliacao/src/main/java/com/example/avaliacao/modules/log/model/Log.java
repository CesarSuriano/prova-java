/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.avaliacao.modules.log.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Cesar
 */
@Entity
@Table(name = "LOG")
@SequenceGenerator(name = "seq_log", sequenceName = "seq_log", initialValue = 1, allocationSize = 1)
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String operacao;
    private Date dataAtual;
    
    @Column(length = 1000)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected Log() {
    }

    public Log(String operacao, Date dataAtual, String descricao) {
        this.operacao = operacao;
        this.dataAtual = dataAtual;
        this.descricao = descricao;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
