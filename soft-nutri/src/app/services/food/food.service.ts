import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment as e } from 'src/environments/environment'; 
import { ConstService } from '../shared/const.service';
import { Food } from 'src/app/model';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
@Injectable({
  providedIn: 'root'
})
export class FoodService {

  constructor(
    private http: HttpClient,
    private api:ConstService
  ) { }

  listAll(): Observable<any> { 
    return this.http.get(this.api.rest.people.listall);
  }
  save(obj:Food): Observable<any> {
        return this.http.post(this.api.rest.food.save, obj );
  }
}