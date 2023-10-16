import { Snack } from "../snack/snack";
import { FoodMenu } from "./foodMenu";

export class SnackMenu {
    idSnackMenu!: number;
    listSnackMenu: Array<Snack> = new Array;
    listBreakFast: Array<FoodMenu> = new Array;
}