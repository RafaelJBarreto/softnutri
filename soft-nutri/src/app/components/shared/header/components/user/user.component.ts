import { UserSimple } from 'src/app/model';

import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent {
  @Input() user!: UserSimple;
  @Output() signOut: EventEmitter<void> = new EventEmitter<void>(); 



  public signOutEmit(): void {
    this.signOut.emit();
  }
}
