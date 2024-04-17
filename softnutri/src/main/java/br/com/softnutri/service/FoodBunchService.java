package br.com.softnutri.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softnutri.domain.Bunch;
import br.com.softnutri.domain.Food;
import br.com.softnutri.domain.FoodBunch;
import br.com.softnutri.exception.SoftNutriException;
import br.com.softnutri.interfaces.FoodBunchReturn;
import br.com.softnutri.records.BunchDTO;
import br.com.softnutri.records.CompositionTableDTO;
import br.com.softnutri.records.FoodBunchDTO;
import br.com.softnutri.records.FoodDTO;
import br.com.softnutri.records.NutritionalDataDTO;
import br.com.softnutri.repository.FoodBunchRepository;

@Service
public class FoodBunchService {
	
	private final FoodBunchRepository foodBunchRepository;

	@Autowired
	public FoodBunchService(FoodBunchRepository foodBunchRepository) {
		this.foodBunchRepository = foodBunchRepository;
	}
	
	public void save(FoodBunchDTO foodBunchDTO) { 
		try {
			final List<FoodBunch> getAllFoodBunch = this.foodBunchRepository.findAll();
			final Bunch bunch = Bunch.builder().idBunch(foodBunchDTO.bunch().idBunch()).description(foodBunchDTO.bunch().description()).build();
			
			foodBunchDTO.foods().stream().map(foodDTO -> Food.builder().idFood(foodDTO.idFood()).build()).forEach(food -> {
				final FoodBunch foodBunch = getAllFoodBunch.stream().filter(x -> food.getIdFood().equals(x.getFood().getIdFood())).findAny().orElse(null);
				this.foodBunchRepository.save(new FoodBunch(foodBunch != null ? foodBunch.getIdFoodBunch() : null, food, bunch));
			});
			
		} catch (Exception e) {
			throw new SoftNutriException("FOODBUNCH.ERROR_SAVE", e);
		}
	}
	
	public List<FoodBunchDTO> getAll() {
		try {	
			return this.converter(this.foodBunchRepository.getAll());
		} catch (Exception e) {
			throw new SoftNutriException("FOODBUNCH.ERROR_LIST", e);
		}
	}
	
	public void delete(Long id) {
		try {
			final Optional<FoodBunch> foodBunch = this.foodBunchRepository.findById(id);
			foodBunch.ifPresent(foodBunchRepository::delete);
		} catch (Exception e) {
			throw new SoftNutriException("BUNCH.BUNCH_REMOVE_ERROR", e);
		}	
	}
	
	public List<FoodBunchDTO> getFoodTable(Long idCompositionTable) {
		try {
			return this.converter(this.foodBunchRepository.getFoodTable(idCompositionTable));
		} catch (Exception e) {
			throw new SoftNutriException("FOODBUNCH.ERROR_LIST_FOOD", e);
		}
	}
	
	private List<FoodBunchDTO> converter(List<FoodBunchReturn> list){
		return list.stream().map(o -> new FoodBunchDTO(o.getIdFoodBunch(), new BunchDTO(o.getIdBunch(), o.getDescription()), new FoodDTO(o.getIdFood(), o.getDescriptionFood(), o.getDescriptionPreparation(), 
				new CompositionTableDTO(o.getIdCompositionTable(), o.getNameTable(), o.getDescriptionPreparation()), 
				new NutritionalDataDTO(o.getCalories(), o.getProtein(), o.getLipids(), o.getCarbohydrate())), Collections.emptyList())).toList();
	}
	 
}
