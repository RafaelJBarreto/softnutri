import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { AuthPageComponent } from './containers/auth-page/auth-page.component';

const routes: Routes = [
  {
    path: '',
    component: AuthPageComponent
    
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})

export class AuthRoutingModule {
}
