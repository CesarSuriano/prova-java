/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.avaliacao.modules.log.repository;

import com.example.avaliacao.modules.log.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cesar
 */
public interface LogRepository extends JpaRepository<Log, Long>{
    
}