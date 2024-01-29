import { Observable } from 'rxjs';
import { Bunch } from 'src/app/model';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ConstService } from '../shared/const.service';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
@Injectable({
  providedIn: 'root'
})
export class BunchService { 

  constructor(
    private http: HttpClient,
    private api:ConstService
  ) { }

  listAll(): Observable<any> { 
    return this.http.get(this.api.rest.bunch.listall);
  }
  saveBunch(bunch: Bunch): Observable<any> {
    return this.http.post(this.api.rest.bunch.save, bunch );
  }
  listAllBunch(): Observable<any> { 
    return this.http.get(this.api.rest.bunch.listall);
  }

  delete(id:any): Observable<any> {
    return this.http.delete(this.api.rest.bunch.delete + id );
  } 
}