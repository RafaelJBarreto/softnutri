import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './components/pages/not-found/not-found.component';
import { LayoutComponent } from './components/shared/layout/layout.component';
import { AuthGuard } from './components/pages/auth/guards';

const routes: Routes = [
  {
    path: 'dashboard',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: LayoutComponent
  },
  {
    path: 'login',
    loadChildren: () => import('./components/pages/auth/auth.module').then(m => m.AuthModule)
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
