import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
//import { JwtHelperService } from '@auth0/angular-jwt';

import { environment as e } from '../../../environments/environment';
import { LoginRequest, SignupRequest } from 'src/app/model';
import { TokenStorageService } from '../';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
 //   public jwtHelper: JwtHelperService,
    private tokenStorageService: TokenStorageService
  ) { }

  login(userLogin: LoginRequest): Observable<any> {
    return this.http.post(e.api.rota + e.api.usuario.autenticar, userLogin, httpOptions);
  } 

  refreshToken(token: string) {
    return this.http.post(e.api.rota+e.api.usuario.refreshtoken, {
      refreshToken: token
    }, httpOptions);
  }

  public isAuthenticated(): boolean {
    const token = this.tokenStorageService.getToken();

    if(token != undefined) {
      return null;// !this.jwtHelper.isTokenExpired(token);
    } else {
      return false;
    }
  }

}