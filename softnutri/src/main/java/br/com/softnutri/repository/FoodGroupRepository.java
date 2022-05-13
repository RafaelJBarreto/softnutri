package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.FoodGroup;

@Repository
public interface FoodGroupRepository extends JpaRepository<FoodGroup, Long>{

}
