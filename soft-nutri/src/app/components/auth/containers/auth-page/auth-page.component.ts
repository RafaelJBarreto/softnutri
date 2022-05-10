import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

import { AuthService } from 'src/app/services/';
import { routes } from 'src/app/consts/routes';
import { MyErrorStateMatcher } from 'src/app/errors';
import { JwtResponse } from 'src/app/model';
import { ConstService } from 'src/app/services/shared/const.service';

@Component({
  selector: 'app-auth-page',
  templateUrl: './auth-page.component.html',
  styleUrls: ['./auth-page.component.scss']
})
export class AuthPageComponent {
  public currentYear: number = new Date().getFullYear();
  public routers: typeof routes = routes;  
  matcher = new MyErrorStateMatcher(); 
  errorMessage = '';

  constructor(
    private service: AuthService,
    private snackBar: MatSnackBar,
    private router: Router,
    private jwtConst:ConstService
  ) { }

  public sendLoginForm(sign: any): void {
    console.log(sign);
    this.service.login(sign).subscribe({
      next: data => {
        localStorage.setItem('token', data['token']); 
        localStorage.setItem('refreshToken', data['refreshToken']);
        this.jwtConst.setTokenVar(data['token']);
        this.jwtConst.setRefreshTokenVar(data['refreshToken']);
        this.jwtConst.setTypeVar(data['type']);
        this.jwtConst.setRolesVar(data['roles']);
        this.jwtConst.setLanguageVar(data['language']);
        this.jwtConst.setExpirationVar(data['expiration']);   
        this.router.navigate([this.routers.DASHBOARD]).then();
      },
      error: err => { 
        this.errorMessage = err.message; 
        this.snackBar.open('Login ou senha inválido', '', {
          horizontalPosition: 'center',
          verticalPosition: 'bottom',
          duration: 10000
        });
      }
    });;

  } 

  public sendSignForm(): void {
    //this.service.sign();

    this.router.navigate([this.routers.DASHBOARD]).then();
  }
}