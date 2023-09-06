import { NutritionalData } from "./nutritional-data";

export class Food {
    idFood!: number;
    description!: string;
    descriptionPreparation!: string;
    nutritionalData: NutritionalData = new NutritionalData;  
}