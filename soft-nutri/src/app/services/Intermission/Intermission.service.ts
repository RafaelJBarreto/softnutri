import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ConstService } from '../shared/const.service';
import { Intermission } from 'src/app/model/Intermission/Intermission';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
@Injectable({
  providedIn: 'root'
})
export class IntermissionService { 

  constructor(
    private http: HttpClient,
    private api:ConstService
  ) { }

  save(obj:Intermission): Observable<any> {
    return this.http.post(this.api.rest.intermission.save, obj );
  } 

  get(): Observable<any> {
    return this.http.get(this.api.rest.intermission.get);
  } 

}