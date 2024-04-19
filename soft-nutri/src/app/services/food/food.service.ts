import { Observable, firstValueFrom } from 'rxjs';
import { Food } from 'src/app/model';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ConstService } from '../shared/const.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
@Injectable({
  providedIn: 'root',
})
export class FoodService {
  constructor(private http: HttpClient, private api: ConstService) {}

  listAll(): Promise<any> {
    return firstValueFrom(this.http.get(this.api.rest.food.listall));
  }

  save(obj: Food): Promise<any> {
    return firstValueFrom(this.http.post(this.api.rest.food.save, obj));
  }
  delete(id: any): Promise<any> {
    return firstValueFrom(this.http.delete(this.api.rest.food.delete + id));
  }
}
