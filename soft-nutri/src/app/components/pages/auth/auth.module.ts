import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTabsModule } from '@angular/material/tabs';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginFormComponent, SearchFormComponent } from './components';
import { AuthPageComponent } from './containers/auth-page/auth-page.component';
import { AuthService} from './services';
import { AuthGuard } from './guards';

@NgModule({
  declarations: [
    AuthPageComponent,
    LoginFormComponent,
    SearchFormComponent,
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    MatTabsModule,
    MatButtonModule,
    MatInputModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    AuthService,
    AuthGuard
  ]
})
export class AuthModule { }
