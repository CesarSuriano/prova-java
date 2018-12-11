/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.polls.repository;

import com.example.polls.model.Role;
import com.example.polls.model.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author cesar
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<RoleName> findByName(RoleName roleName);
}
