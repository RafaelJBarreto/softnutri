package br.com.softnutri.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class NutritionalData implements Serializable {

	private static final long serialVersionUID = 1L;

	private float calories = 0;
	private float protein = 0;
	private float lipids = 0;
	private float carbohydrate = 0;

}
