import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { FoodsComponent } from './components/foods/foods.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { PersonActionComponent } from './components/person/person-action/person-action.component';
import { PersonComponent } from './components/person/person.component';
import { LayoutComponent } from './components/shared/layout/layout.component';
import { AuthGuardService as AuthGuard  } from './services';

const routes: Routes = [
  {
    path: 'dashboard',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: LayoutComponent
  },
  {
    path: 'food',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: FoodsComponent
  },
  {
    path: 'patient',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: PersonComponent
  }, {
    path: 'patientaction',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: PersonActionComponent
  },
  {
    path: 'patientaction/:id',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: PersonActionComponent
  },
  {
    path: 'login',
    loadChildren: () => import('./components/auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: '404',
    component: NotFoundComponent
  },
  {
    path: '**',
    redirectTo: '404'
  }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      useHash: true,
      preloadingStrategy: PreloadAllModules,
      relativeLinkResolution: 'legacy'
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
