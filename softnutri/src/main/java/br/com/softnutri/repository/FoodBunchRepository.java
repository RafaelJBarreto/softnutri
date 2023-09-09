package br.com.softnutri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softnutri.domain.FoodBunch;
import br.com.softnutri.interfaces.FoodBunchReturn;

@Repository
public interface FoodBunchRepository extends JpaRepository<FoodBunch, Long>{
	
	@Query(value = "select fb.id_food_bunch as idFoodBunch, b.id_bunch as idBunch, b.description, f.id_food as idFood, f.description as descriptionFood, f.calories, f.protein, f.lipids, f.carbohydrate, f.description_preparation as descriptionPreparation, ct.id_composition_table as idCompositionTable, ct.name as nameTable, ct.description as descriptionTable  from food f left join food_bunch fb on fb.id_food = f.id_Food left join bunch b on b.id_bunch = fb.id_bunch left join composition_table ct on ct.id_composition_table = f.id_composition_table" , nativeQuery=true)
	List<FoodBunchReturn> getAll();
	
	@Query(value = "select fb.id_food_bunch as idFoodBunch, b.id_bunch as idBunch, b.description, f.id_food as idFood, f.description as descriptionFood, f.calories, f.protein, f.lipids, f.carbohydrate, f.description_preparation as descriptionPreparation, ct.id_composition_table as idCompositionTable, ct.name as nameTable, ct.description as descriptionTable  from food f left join food_bunch fb on fb.id_food = f.id_Food left join bunch b on b.id_bunch = fb.id_bunch left join composition_table ct on ct.id_composition_table = f.id_composition_table where ct.id_composition_table = :idCompositionTable" , nativeQuery=true)
	List<FoodBunchReturn> getFoodTable(@Param("idCompositionTable") Long idCompositionTable);

}
