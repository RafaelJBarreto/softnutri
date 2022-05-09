import { Injectable, EventEmitter } from '@angular/core';

import { JwtResponse } from 'src/app/model';

@Injectable({
  providedIn: 'root'
})
export class UserInfoService {

  showMenu = new EventEmitter<boolean>();
  user = new EventEmitter<JwtResponse>();

  constructor() { }

  setValueUser(u: JwtResponse): void {
    this.user.emit(u);
  }

  alterValue(value: boolean): void {
    this.showMenu.emit(value);
  }
  
}