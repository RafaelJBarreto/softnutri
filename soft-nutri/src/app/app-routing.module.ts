import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { CalendarComponent } from './components/calendar/calendar.component';
import { CalendaractionComponent } from './components/calendar/calendaraction/calendaraction.component';
import { FoodsComponent } from './components/foods/foods.component';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { PersonActionComponent } from './components/person/person-action/person-action.component';
import { PersonComponent } from './components/person/person.component';
import { ProfessionalActionComponent } from './components/professional/professional-action/professional-action.component';
import { ProfessionalComponent } from './components/professional/professional.component';
import { AuthGuardService as AuthGuard  } from './services';
import { PermissionComponent } from './components/permission/permission.component';
import { TreatmentComponent } from './components/treatment/treatment.component';
import { TableComponent } from './components/table/table.component';
import { TableActionComponent } from './components/table/table-action/table-action.component';
import { SnackComponent } from './components/snack/snack.component';
import { SnackActionComponent } from './components/snack/snack-action/snack-action.component';

const routes: Routes = [
  {
    path: 'home',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: HomeComponent
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
    path: 'professional',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: ProfessionalComponent
  },
  {
    path: 'professionalaction',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: ProfessionalActionComponent
  },
  {
    path: 'professionalaction/:id',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: ProfessionalActionComponent
  },
  {
    path: 'calendar',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: CalendarComponent
  },
  {
    path: 'calendaraction',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: CalendaractionComponent
  },
  {
    path: 'calendaraction/:id',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: CalendaractionComponent
  }, 
  {
    path: 'permission',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: PermissionComponent
  },
  {
    path: 'menu',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: TreatmentComponent
  }, {
    path: 'table',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: TableComponent
  }, 
  {
    path: 'tableaction',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: TableActionComponent
  }, 
  {
    path: 'tableaction/:id',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: TableActionComponent
  }, 
  {
    path: 'snack',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: SnackComponent
  },
  {
    path: 'snackaction',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: SnackActionComponent
  }, 
  {
    path: 'snackaction/:id',
    pathMatch: 'full',
    canActivate: [AuthGuard],
    component: SnackActionComponent
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
    component: NotFoundComponent
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
