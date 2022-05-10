import { Component, EventEmitter, Input, Output } from '@angular/core';
import { User } from 'src/app/components/pages/auth/models/user';
import { routes } from 'src/app/consts/routes';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent {
  @Input() user!: User;
  @Output() signOut: EventEmitter<void> = new EventEmitter<void>();
  public routes: typeof routes = routes;

  public signOutEmit(): void {
    this.signOut.emit();
  }
}
