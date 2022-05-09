import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

import { AuthService } from '../../services';
import { routes } from '../../../../consts';
import { JwtResponse } from 'src/app/model';
import { MyErrorStateMatcher } from 'src/app/errors';

@Component({
  selector: 'app-auth-page',
  templateUrl: './auth-page.component.html',
  styleUrls: ['./auth-page.component.scss']
})
export class AuthPageComponent {
  public todayDate: Date = new Date();
  public routers: typeof routes = routes; 
  jwtResponse:JwtResponse;
  matcher = new MyErrorStateMatcher(); 
  errorMessage = '';

  constructor(
    private service: AuthService,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  public sendLoginForm(sign): void {
    console.log(sign);
    this.service.login(sign).subscribe({
      next: data => {
        localStorage.setItem('token', data['token']); 
        localStorage.setItem('refreshToken', data['refreshToken']);
        this.jwtResponse= {
          token:data['token'],
          type:data['type'],  
          roles:data['roles'],  
          language:data['language'],  
          expiration:data['expiration'],  
        };
        localStorage.setItem('loginData', JSON.parse(JSON.stringify(this.jwtResponse)));   
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
    this.service.sign();

    this.router.navigate([this.routers.DASHBOARD]).then();
  }
}
