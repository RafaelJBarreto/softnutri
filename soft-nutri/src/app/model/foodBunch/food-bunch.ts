import { Food } from "../food/food";
import { Bunch } from "../food/bunch";

export class FoodBunch {
    bunch!: Bunch;
    food!: Food;
    foods!: Array<Food>;
}