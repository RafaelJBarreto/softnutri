package br.com.softnutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.Nutritionist;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Long> {

}
