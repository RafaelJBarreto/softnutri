import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

import { AuthService } from 'src/app/services/';
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
  matcher = new MyErrorStateMatcher();
  errorMessage = '';

  constructor(
    private service: AuthService,
    private snackBar: MatSnackBar,
    private router: Router,
    private global: ConstService
  ) { }

  public sendLoginForm(sign: any): void { 
    this.service.login(sign).subscribe({
      next: data => {
        this.global.setTokenVar(data['token']);
        this.global.setRefreshTokenVar(data['refreshToken']);
        this.global.setTypeVar(data['type']);
        this.global.setRolesVar(data['roles']);
        this.global.setLanguageVar(data['language']);
        this.global.setExpirationVar(data['expiration']);
        this.router.navigate([this.global.redirect.FOOD]).then();
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open('Login ou senha inválido', '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000,
          panelClass: ['warning']
        });
      }
    });;

  }

  public sendSignForm(): void {
    //this.service.sign();

    this.router.navigate([this.global.redirect.FOOD]).then();
  }
}