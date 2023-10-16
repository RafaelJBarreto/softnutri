
export class FoodMenu {
    id!: number;
    idSnackMenu!: number;
    idFood!: number;
    amount!:number;
    calories!: number;
    protein!:number;
    lipids!:number;
    carbohydrate!:number;

    constructor(id: number, idSnackMenu: number, idFood: number, amount: number, calories: number, protein: number, lipids: number, carbohydrate: number){
        this.id = id;
        this.idSnackMenu = idSnackMenu;
        this.idFood = idFood;
        this.amount = amount;
        this.calories = calories;
        this.protein = protein;
        this.lipids = lipids;
        this.carbohydrate = carbohydrate;
    }
}