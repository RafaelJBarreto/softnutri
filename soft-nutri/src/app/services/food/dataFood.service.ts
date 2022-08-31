import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Food } from 'src/app/model';

@Injectable()
export class DataFoodService {

  private foods = new BehaviorSubject(Food);
  foodsData = this.foods.asObservable();
  selectedsFoods: Array<Food> = [];

  constructor() { }

  public updateFoodSelected(data: any, marcado: boolean) {
    this.foods.next(data);
    if(marcado){
      if(this.selectedsFoods.filter(x => x.idFood == data.idFood).length == 0){
        this.selectedsFoods.push(data);
      }
    }else{
      this.selectedsFoods = this.selectedsFoods.filter(x => x.idFood != data.idFood);
    }
  }

  getSelectedFoods(): Array<Food>{
     return this.selectedsFoods;
  }

  reset(): Array<Food>{
    return this.selectedsFoods = [];
 }
}