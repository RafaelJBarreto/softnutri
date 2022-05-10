import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
 
import { LogOutRequest } from 'src/app/model';
import { UserInfoService,UserService } from '../index'; 
import { ConstService } from '../shared/const.service';

const TOKEN_KEY = 'token';
const USER_KEY = 'loginData';
const REFRESHTOKEN_KEY = 'refreshToken';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor(
    private userInfoService: UserInfoService,
    private userService: UserService,
    private snackBar: MatSnackBar,
    private jwtConst: ConstService
  ) { }

  public signOut(): void {
    let logOutRequest: LogOutRequest = {userId: this.getUser().id};
    this.userService.logoutUser(logOutRequest).subscribe({
      error: err => {
        this.snackBar.open(err.menssage, 'Ok', {
          horizontalPosition: 'center',
          verticalPosition: 'bottom',
          duration: 10000
        });
      }
    });
    localStorage.clear();
    this.userInfoService.alterValue(false);
  }

  public saveToken(token: string): void {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.setItem(TOKEN_KEY, token);

    const user = this.getUser();
    if (user.id) {
      this.saveUser({ ...user, accessToken: token });
    }
  }

  public getToken(): string | null {
    console.log("xuxu viado");
    var token = this.jwtConst.getTokenVar();
    console.log(token);

    if (token != null) {
      this.userInfoService.alterValue(true);
    }
    return token;
  }

  public saveUser(user: any): void {
    localStorage.removeItem(USER_KEY);
    localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = localStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return {};
  }

  public saveRefreshToken(token: string): void {
    localStorage.removeItem(REFRESHTOKEN_KEY);
    localStorage.setItem(REFRESHTOKEN_KEY, token);
  }

  public getRefreshToken(): string | null {
    return localStorage.getItem(REFRESHTOKEN_KEY);
  }

  public getIdUser(): number {
    let userId = this.getUser().id;
    return userId;
  }

}