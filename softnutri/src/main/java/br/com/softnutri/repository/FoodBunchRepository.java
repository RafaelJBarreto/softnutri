package br.com.softnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.FoodBunch;
import br.com.softnutri.util.FoodBunchReturn;

@Repository
public interface FoodBunchRepository extends JpaRepository<FoodBunch, Long>{
	
	@Query(value = "select fb.id_food_bunch as idFoodBunch, b.id_bunch as idBunch, b.description, f.id_food as idFood, f.description as descriptionFood, f.calories, f.protein, f.lipids, f.carbohydrate  from food f left join food_bunch fb on fb.id_food = f.id_Food left join bunch b on b.id_bunch = fb.id_bunch", nativeQuery=true)
	List<FoodBunchReturn> getAll();

}
