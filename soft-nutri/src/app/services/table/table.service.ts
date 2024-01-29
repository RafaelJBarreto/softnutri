import { Observable } from 'rxjs';
import { Table } from 'src/app/model/table/table';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ConstService } from '../shared/const.service';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
@Injectable({
  providedIn: 'root'
})
export class TableService { 

  constructor(
    private http: HttpClient,
    private api:ConstService
  ) { }

  listAll(): Observable<any> { 
    return this.http.get(this.api.rest.table.listall);
  }

  save(obj:Table): Observable<any> {
    return this.http.post(this.api.rest.table.save, obj );
  } 

  get(idTable:any): Observable<any> {
    return this.http.get(this.api.rest.table.get + idTable );
  } 

  delete(idTable:any): Observable<any> {
    return this.http.delete(this.api.rest.table.delete + idTable );
  } 

}