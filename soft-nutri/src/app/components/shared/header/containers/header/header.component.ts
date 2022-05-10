import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router'; 
import { AuthService } from 'src/app/services';
import { ConstService } from 'src/app/services/shared/const.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})

export class HeaderComponent {
  @Input() isMenuOpened!: boolean;
  @Output() isShowSidebar = new EventEmitter<boolean>();
  //public user$: Observable<User> 

  constructor(
    private authService: AuthService,
    private router: Router,
    private global: ConstService
  ) {
    //this.user$ = this.userService.getUser();
  }

  public openMenu(): void {
    this.isMenuOpened = !this.isMenuOpened;

    this.isShowSidebar.emit(this.isMenuOpened);
  }

  public signOut(): void {
    this.authService.signOut();

    this.router.navigate([this.global.redirect.LOGIN]);
  }
}