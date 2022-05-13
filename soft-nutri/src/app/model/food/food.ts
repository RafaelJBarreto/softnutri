import { NutritionalData } from "./nutritional-data";

export class Food {
    idFood!: number;
    description!: string;
    nutritionalData: NutritionalData = new NutritionalData; 
}