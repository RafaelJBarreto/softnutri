import { Observable, firstValueFrom } from 'rxjs';
import { User } from 'src/app/model/user/user';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ConstService } from '../shared/const.service';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
@Injectable({
  providedIn: 'root'
})
export class ProfessionalService { 

  constructor(
    private http: HttpClient,
    private api:ConstService
  ) { }

  listAll(): Promise<any> { 
    return firstValueFrom(this.http.get(this.api.rest.professional.listall));
  }

  save(obj:User): Promise<any> {
    return firstValueFrom(this.http.post(this.api.rest.professional.save, obj ));
  } 

  get(idPerson:any): Promise<any> {
    return firstValueFrom(this.http.get(this.api.rest.professional.get + idPerson ));
  } 

  delete(idPerson:any): Promise<any> {
    return firstValueFrom(this.http.delete(this.api.rest.professional.delete + idPerson ));
  } 

  getNutritionist(): Promise<any> { 
    return firstValueFrom(this.http.get(this.api.rest.professional.nutritionist));
  }

}