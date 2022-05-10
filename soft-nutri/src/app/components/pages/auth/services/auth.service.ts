import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { SignIn } from 'src/app/model';
import { environment as e } from 'src/environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(
    private http: HttpClient
  ) { }
  
  public login(signin: SignIn): Observable<any> { 
    return this.http.post(e.api.rota+e.api.usuario.autenticar, signin, httpOptions); 
  }

  public sign(): void {
    localStorage.setItem('token', 'token');
  }

  public signOut(): void {
    localStorage.removeItem('token');
  }

}