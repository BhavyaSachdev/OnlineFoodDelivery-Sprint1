package com.capg.ofda.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capg.ofda.entities.Food;

@Repository

public interface IFoodRepositoryDao extends JpaRepository<Food, Integer> {
		
		String deleteById(int foodId);
	}


