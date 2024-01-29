import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { NgxMaskModule } from 'ngx-mask';
import { NgxMatTimepickerModule } from 'ngx-mat-timepicker';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';

import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatBadgeModule } from '@angular/material/badge';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatChipsModule } from '@angular/material/chips';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { RouterModule } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FlexLayoutModule } from '@ngbracket/ngx-layout';
import { TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { authInterceptorProviders } from '../interceptor/auth.interceptor';
import { ConstService } from '../services/shared/const.service';
import { PaginatorI18n } from '../services/shared/paginators/paginatorI18n';
import { AuthModule } from './auth/auth.module';
import {
    CalendarDraggableComponent
} from './calendar/calendar-draggable/calendar-draggable.component';
import { CalendarComponent } from './calendar/calendar.component';
import { CalendaractionComponent } from './calendar/calendaraction/calendaraction.component';
import { CancelCalendarComponent } from './calendar/cancel-calendar/cancel-calendar.component';
import { TimeCalendarComponent } from './calendar/time-calendar/time-calendar.component';
import {
    FoodGroupAssociationComponent
} from './dialog/form/food-group-association/food-group-association.component';
import { FoodGroupComponent } from './dialog/form/food-group/food-group.component';
import { FoodComponent } from './dialog/form/food/food.component';
import { RemoveDialogComponent } from './dialog/form/remove-dialog/remove-dialog.component';
import { FoodsComponent } from './foods/foods.component';
import { HomeComponent } from './home/home.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { PermissionComponent } from './permission/permission.component';
import { PersonActionComponent } from './person/person-action/person-action.component';
import { PersonDeleteComponent } from './person/person-delete/person-delete.component';
import { PersonComponent } from './person/person.component';
import { PhoneDetailComponent } from './phone-detail/phone-detail.component';
import {
    ProfessionalActionComponent
} from './professional/professional-action/professional-action.component';
import {
    ProfessionalDeleteComponent
} from './professional/professional-delete/professional-delete.component';
import { ProfessionalComponent } from './professional/professional.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HeaderModule } from './shared/header/containers/header/header.module';
import { LayoutComponent } from './shared/layout/layout.component';
import { NutritionalDataComponent } from './shared/nutrional-data/nutritional-data.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';
import { SnackActionComponent } from './snack/snack-action/snack-action.component';
import { SnackDeleteComponent } from './snack/snack-delete/snack-delete.component';
import { SnackComponent } from './snack/snack.component';
import { TableActionComponent } from './table/table-action/table-action.component';
import { TableDeleteComponent } from './table/table-delete/table-delete.component';
import { TableComponent } from './table/table.component';
import { AttendanceComponent } from './treatment/attendance/attendance.component';
import {
    FoodAttendanceComponent
} from './treatment/attendance/food-attendance/food-attendance.component';
import {
    CalendarProfessionalComponent
} from './treatment/calendar-professional/calendar-professional.component';
import { TreatmentComponent } from './treatment/treatment.component';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    NotFoundComponent,
    FoodsComponent,
    FoodComponent,
    SidebarComponent,
    FooterComponent,
    LayoutComponent,
    NutritionalDataComponent,
    FoodGroupComponent,
    FoodGroupAssociationComponent,
    PersonComponent,
    PersonActionComponent,
    PersonDeleteComponent,
    ProfessionalComponent,
    ProfessionalActionComponent,
    HomeComponent,
    PhoneDetailComponent,
    ProfessionalDeleteComponent,
    RemoveDialogComponent,
    CalendarComponent,
    CalendaractionComponent,
    TimeCalendarComponent,
    CancelCalendarComponent,
    CalendarProfessionalComponent,
    AttendanceComponent,
    FoodAttendanceComponent,
    PermissionComponent,
    TreatmentComponent,
    TableComponent,
    TableActionComponent,
    TableDeleteComponent,
    SnackComponent,
    SnackActionComponent,
    SnackDeleteComponent,
    CalendarDraggableComponent]
    ,
  imports: [
    MatListModule,
    TranslateModule.forRoot({
      loader: { provide: TranslateLoader, useFactory: HttpLoaderFactory, deps: [HttpClient] },
      isolate: true,
      defaultLanguage: 'pt-BR'
    }),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
    MatIconModule,
    RouterModule,
    MatButtonModule,
    CommonModule,
    MatMenuModule,
    MatSelectModule,
    FormsModule,
    MatSidenavModule,
    HeaderModule,
    ReactiveFormsModule,
    MatCardModule,
    MatButtonModule,
    HttpClientModule,
    MatSnackBarModule,
    RouterModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatInputModule,
    MatPaginatorModule, 
    MatCheckboxModule,
    MatTableModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    AuthModule,
    MatGridListModule,
    NgxMaskModule,
    FontAwesomeModule,
    MatBadgeModule,
    MatTooltipModule,
    MatAutocompleteModule,
    NgxMatTimepickerModule,
    MatSlideToggleModule,
    MatExpansionModule, 
    MatButtonToggleModule,
    MatTabsModule,
    MatChipsModule,
    NgxMaterialTimepickerModule,
    FlexLayoutModule
  ],
  exports: [
    SidebarComponent,
    HeaderModule,
    LayoutComponent
  ],
  providers:[ authInterceptorProviders,ConstService,
    {
      provide: MatPaginatorIntl, deps: [TranslateService],
      useFactory: (translateService: TranslateService) => new PaginatorI18n(translateService).getPaginatorIntl()
    }
  ]
})
export class ComponentsModule { }
