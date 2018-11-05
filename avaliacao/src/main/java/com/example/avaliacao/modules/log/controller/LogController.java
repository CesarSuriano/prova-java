/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.avaliacao.modules.log.controller;

import com.example.avaliacao.modules.employee.model.Employee;
import com.example.avaliacao.modules.log.model.Log;
import com.example.avaliacao.modules.log.repository.LogRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cesar
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LogController {

    @Autowired
    LogRepository logRepository;

    @GetMapping("/logs")
    public List<Log> getAllLogs() {
        try {
            Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "id");
            return logRepository.findAll(sortByCreatedAtDesc);

        } catch (Exception e) {
            System.out.println("Erro ao buscar o log: " + e.getMessage());
        }

        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Log createLog(Log log) {
        try {
            return logRepository.save(log);
        } catch (Exception e) {
            System.out.println("Erro ao salvar o log: " + e.getMessage());
        }
        return null;

    }

}
