import { AuthService } from 'src/app/services/';
import { ConstService } from 'src/app/services/shared/const.service';

import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-auth-page',
  templateUrl: './auth-page.component.html',
  styleUrls: ['./auth-page.component.scss']
})
export class AuthPageComponent {
  public currentYear: number = new Date().getFullYear();
  errorMessage = '';

  constructor(
    private service: AuthService,
    private snackBar: MatSnackBar,
    private router: Router,
    private global: ConstService
  ) { 
  }

  public sendLoginForm(sign: any): void { 
    this.service.login(sign).subscribe({
      next: data => {
        this.global.setEmail(data['email']);
        this.global.setTokenVar(data['token']);
        this.global.setRefreshTokenVar(data['refreshToken']);
        this.global.setTypeVar(data['type']);
        this.global.setRolesVar(data['roles']);
        this.global.setLanguageVar(data['language']);
        this.global.setExpirationVar(data['expiration']);
        this.global.setModuleList(data['modules']);
        this.router.navigate([this.global.redirect.HOME]).then();
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open('Login ou senha inválido', '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000
        });
      }
    });;

  }

  public sendSignForm(): void {
    this.router.navigate([this.global.redirect.HOME]).then();
  }
}