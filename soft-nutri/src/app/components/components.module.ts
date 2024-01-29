import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FoodComponent } from './dialog/form/food/food.component';
import { FoodsComponent } from './foods/foods.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSidenavModule } from '@angular/material/sidenav';
import { HeaderModule } from './shared/header/containers/header/header.module'; 
import { AuthModule } from './auth/auth.module';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { LayoutComponent } from './shared/layout/layout.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component'; 
import { FooterComponent } from './shared/footer/footer.component';
import { TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core'; 
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { PaginatorI18n } from '../services/shared/paginators/paginatorI18n';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { NutritionalDataComponent } from './shared/nutrional-data/nutritional-data.component';
import { authInterceptorProviders } from '../interceptor/auth.interceptor';
import { ConstService } from '../services/shared/const.service';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatTableModule } from '@angular/material/table';
import { FoodGroupComponent } from './dialog/form/food-group/food-group.component';
import { FoodGroupAssociationComponent } from './dialog/form/food-group-association/food-group-association.component';
import { PersonComponent } from './person/person.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { PersonActionComponent } from './person/person-action/person-action.component';
import { MatNativeDateModule } from '@angular/material/core';
import {MatRadioModule} from '@angular/material/radio';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatGridListModule} from '@angular/material/grid-list';
import { PersonDeleteComponent } from './person/person-delete/person-delete.component';
import { NgxMaskModule } from 'ngx-mask';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {MatBadgeModule} from '@angular/material/badge';
import { ProfessionalComponent } from './professional/professional.component';
import { ProfessionalActionComponent } from './professional/professional-action/professional-action.component';
import { HomeComponent } from './home/home.component';
import { ProfessionalDeleteComponent } from './professional/professional-delete/professional-delete.component';
import { PhoneDetailComponent } from './phone-detail/phone-detail.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { RemoveDialogComponent } from './dialog/form/remove-dialog/remove-dialog.component';
import { CalendarComponent } from './calendar/calendar.component';
import { CalendaractionComponent } from './calendar/calendaraction/calendaraction.component';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { NgxMatTimepickerModule } from 'ngx-mat-timepicker';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { TimeCalendarComponent } from './calendar/time-calendar/time-calendar.component';
import { CancelCalendarComponent } from './calendar/cancel-calendar/cancel-calendar.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { PermissionComponent } from './permission/permission.component';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import { TreatmentComponent } from './treatment/treatment.component';
import { CalendarProfessionalComponent } from './treatment/calendar-professional/calendar-professional.component';
import { AttendanceComponent } from './treatment/attendance/attendance.component';
import { FoodAttendanceComponent } from './treatment/attendance/food-attendance/food-attendance.component';
import {MatTabsModule} from '@angular/material/tabs';
import { TableComponent } from './table/table.component';
import { TableActionComponent } from './table/table-action/table-action.component';
import { TableDeleteComponent } from './table/table-delete/table-delete.component';
import { SnackComponent } from './snack/snack.component';
import { SnackActionComponent } from './snack/snack-action/snack-action.component';
import { SnackDeleteComponent } from './snack/snack-delete/snack-delete.component';
import {MatChipsModule} from '@angular/material/chips';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { FlexLayoutModule } from '@ngbracket/ngx-layout';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { CalendarDraggableComponent } from './calendar/calendar-draggable/calendar-draggable.component';

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
