import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ConstService } from '../shared/const.service';
import { Food, FoodBunch } from 'src/app/model';

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

  listAll(): Observable<any> { 
    return this.http.get(this.api.rest.foodbunch.listall);
  }
  saveFoodBunch(foodBunch: FoodBunch): Observable<any> {
    return this.http.post(this.api.rest.foodbunch.save, foodBunch );
  }

  listAllBunch(): Observable<any> { 
    return this.http.get(this.api.rest.foodbunch.listall);
  }

  delete(id:any): Observable<any> {
    return this.http.delete(this.api.rest.foodbunch.delete + id );
  } 

  getFoodTable(id:any): Observable<any> {
    return this.http.get(this.api.rest.foodbunch.table + id );
  } 
}