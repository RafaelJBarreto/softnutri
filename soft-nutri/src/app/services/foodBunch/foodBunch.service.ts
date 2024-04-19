import { Observable, firstValueFrom } from 'rxjs';
import { FoodBunch } from 'src/app/model';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ConstService } from '../shared/const.service';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
@Injectable({
  providedIn: 'root'
})
export class FoodBunchService { 

  constructor(
    private http: HttpClient,
    private api:ConstService
  ) { }

  listAll(): Promise<any> { 
    return firstValueFrom(this.http.get(this.api.rest.foodbunch.listall));
  }
  saveFoodBunch(foodBunch: FoodBunch): Promise<any> {
    return firstValueFrom(this.http.post(this.api.rest.foodbunch.save, foodBunch ));
  }
  
  delete(id:any): Promise<any> {
    return firstValueFrom(this.http.delete(this.api.rest.foodbunch.delete + id ));
  } 

  getFoodTable(id:any): Promise<any> {
    return firstValueFrom(this.http.get(this.api.rest.foodbunch.table + id ));
  } 
}
