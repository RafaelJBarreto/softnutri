import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';

import { environment as e } from '../../../environments/environment';
import { LoginRequest, SignIn, SignupRequest } from 'src/app/model';
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
    public jwtHelper: JwtHelperService,
    private tokenStorageService: TokenStorageService
  ) { }

  public login(signin: SignIn): Observable<any> {
    return this.http.post(e.api.rota + e.api.user.autenticar, signin, httpOptions);
  } 

  refreshToken(token: string) {
    return this.http.post(e.api.rota+e.api.user.refreshtoken, {
      refreshToken: token
    }, httpOptions);
  }
  public signOut(): void {
    localStorage.clear();
  }
  public isAuthenticated(): boolean {
    const token = this.tokenStorageService.getToken();

    if(token != undefined) {
      return !this.jwtHelper.isTokenExpired(token);
    } else {
      return false;
    }
  }

}