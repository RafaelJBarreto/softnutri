import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ConstService } from '../shared/const.service';
import { User } from 'src/app/model/user/user';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
@Injectable({
  providedIn: 'root'
})
export class PersonService { 

  constructor(
    private http: HttpClient,
    private api:ConstService
  ) { }

  listAll(): Observable<any> { 
    return this.http.get(this.api.rest.patient.listall);
  }

  save(obj:User): Observable<any> {
    return this.http.post(this.api.rest.patient.save, obj );
  } 

  get(idPerson:any): Observable<any> {
    return this.http.get(this.api.rest.patient.get + idPerson );
  } 

  delete(idPerson:any): Observable<any> {
    return this.http.delete(this.api.rest.patient.delete + idPerson );
  } 

}