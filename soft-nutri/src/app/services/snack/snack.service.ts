import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ConstService } from '../shared/const.service';
import { Table } from 'src/app/model/table/table';
import { Snack } from 'src/app/model/snack/snak';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
@Injectable({
  providedIn: 'root'
})
export class SnackService { 

  constructor(
    private http: HttpClient,
    private api:ConstService
  ) { }

  listAll(): Observable<any> { 
    return this.http.get(this.api.rest.snack.listall);
  }

  save(obj:Snack): Observable<any> {
    return this.http.post(this.api.rest.snack.save, obj );
  } 

  get(idSnack:any): Observable<any> {
    return this.http.get(this.api.rest.snack.get + idSnack );
  } 

  delete(idSnack:any): Observable<any> {
    return this.http.delete(this.api.rest.snack.delete + idSnack );
  } 

}