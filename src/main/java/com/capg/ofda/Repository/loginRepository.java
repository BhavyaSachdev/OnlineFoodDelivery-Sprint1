package com.capg.ofda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.ofda.entities.Login;

public interface loginRepository extends JpaRepository<Login,String>  {

}
