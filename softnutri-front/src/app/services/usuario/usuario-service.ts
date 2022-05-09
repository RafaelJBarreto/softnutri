import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment as e } from 'src/environments/environment';
import { LogOutRequest, SignIn } from 'src/app/model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  getSignIn(signin: SignIn): Observable<any> { 
    return this.http.post(e.api.rota +e.api.usuario.autenticar, signin);
  }

//   alterDataUser(user: User): Observable<any> {
//     return this.http.put(e.AUTH_API + e.USER_CONTROLLER + '/updateUser', user );
//   }

  logoutUser(logOutRequest: LogOutRequest): Observable<any> {
    return this.http.post(e.api.rota +e.api.usuario.logout, logOutRequest);
  }
  
}