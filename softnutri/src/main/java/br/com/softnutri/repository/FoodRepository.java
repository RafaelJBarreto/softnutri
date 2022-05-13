package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>{

}
