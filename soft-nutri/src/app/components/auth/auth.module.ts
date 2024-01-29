import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatTabsModule } from '@angular/material/tabs';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginFormComponent, SearchFormComponent } from './components';
import { AuthPageComponent } from './containers/auth-page/auth-page.component';

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
  ]
})
export class AuthModule { }
