import { Observable, firstValueFrom } from 'rxjs';
import { PersonPaper } from 'src/app/model/permission/personPaper';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ConstService } from '../shared/const.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
@Injectable({
  providedIn: 'root',
})
export class PermissionService {
  constructor(private http: HttpClient, private api: ConstService) {}

  listAll(): Promise<any> {
    return firstValueFrom(this.http.get(this.api.rest.professional.listall));
  }

  save(obj: PersonPaper): Promise<any> {
    return firstValueFrom(this.http.post(this.api.rest.permission.save, obj));
  }
  get(idUser: any): Promise<any> {
    return firstValueFrom(this.http.get(this.api.rest.permission.get + idUser));
  }
}
