import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment as e } from 'src/environments/environment';
import { LogOutRequest, SignIn } from 'src/app/model';
import { ConstService } from '../shared/const.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
    private api:ConstService
  ) { }

  getSignIn(signin: SignIn): Observable<any> { 
    return this.http.post(this.api.rest.user.signin, signin);
  }

//   alterDataUser(user: User): Observable<any> {
//     return this.http.put(e.AUTH_API + e.USER_CONTROLLER + '/updateUser', user );
//   }

  logoutUser(): Observable<any> {
    return this.http.delete(this.api.rest.user.logout);
  }
  
}