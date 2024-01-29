import { Observable } from 'rxjs';
import { SignIn } from 'src/app/model';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

import { TokenStorageService } from '../';
import { ConstService } from '../shared/const.service';

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
    private tokenStorageService: TokenStorageService,
    private api:ConstService
  ) { }

  public login(signin: SignIn): Observable<any> {
    return this.http.post(this.api.rest.user.signin, signin, httpOptions);
  } 

  refreshToken(token: string) {
    return this.http.post(this.api.rest.user.refreshtoken, {
      refreshToken: token
    }, httpOptions);
  }
  public signOut(): void {
    this.tokenStorageService.signOut();
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