import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

import { AuthService, TokenStorageService } from '../';
import { ConstService } from '../shared/const.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(
    public authService: AuthService,
    public router: Router,
    private token: TokenStorageService,
    private global:ConstService
  ) { }

  canActivate(): boolean {
    if(!this.authService.isAuthenticated()) {
      this.router.navigate([this.global.redirect.LOGIN]);
      this.token.signOut();
      return false;
    }
    return true;
  }

}