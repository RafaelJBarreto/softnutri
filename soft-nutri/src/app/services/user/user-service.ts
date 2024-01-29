import { Observable } from 'rxjs';
import { SignIn } from 'src/app/model';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

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