import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment as e } from 'src/environments/environment'; 
const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
@Injectable({
  providedIn: 'root'
})
export class PessoasService {

  constructor(
    private http: HttpClient
  ) { }

  listAll(): Observable<any> { 
    return this.http.get(e.api.rota +e.api.pessoas.listarTodos);
  }
 
  
}