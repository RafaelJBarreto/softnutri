import { CalendarModule } from 'angular-calendar';

import { CommonModule } from '@angular/common';
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
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTimepickerModule } from '@angular/material/timepicker';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';

import { RouterModule } from '@angular/router';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { FlexLayoutModule } from '@ngbracket/ngx-layout';

import {
  NgxMaskDirective,
  NgxMaskPipe,
} from 'ngx-mask';

import {
  TranslateDirective,
  TranslatePipe,
} from '@ngx-translate/core';

import { AuthModule } from './auth/auth.module';

import {
  CalendarDraggableComponent
} from './calendar/calendar-draggable/calendar-draggable.component';

import {
  CalendarFormComponent
} from './calendar/calendar-form/calendar-form.component';

import {
  CalendarListComponent
} from './calendar/calendar-list/calendar-list.component';

import {
  CalendarComponent
} from './calendar/calendar.component';

import {
  CalendaractionComponent
} from './calendar/calendaraction/calendaraction.component';

import {
  CancelCalendarComponent
} from './calendar/cancel-calendar/cancel-calendar.component';

import {
  TimeCalendarComponent
} from './calendar/time-calendar/time-calendar.component';

import {
  FoodGroupAssociationComponent
} from './dialog/form/food-group-association/food-group-association.component';

import {
  FoodGroupComponent
} from './dialog/form/food-group/food-group.component';

import {
  FoodComponent
} from './dialog/form/food/food.component';

import {
  RemoveDialogComponent
} from './dialog/form/remove-dialog/remove-dialog.component';

import { FoodsComponent } from './foods/foods.component';
import { HomeComponent } from './home/home.component';

import { LoaderModule } from './loader/loader.module';

import { NotFoundComponent } from './not-found/not-found.component';

import {
  PermissionComponent
} from './permission/permission.component';

import {
  PersonActionComponent
} from './person/person-action/person-action.component';

import {
  PersonDeleteComponent
} from './person/person-delete/person-delete.component';

import {
  PersonComponent
} from './person/person.component';

import {
  PhoneDetailComponent
} from './phone-detail/phone-detail.component';

import {
  ProfessionalActionComponent
} from './professional/professional-action/professional-action.component';

import {
  ProfessionalDeleteComponent
} from './professional/professional-delete/professional-delete.component';

import {
  ProfessionalComponent
} from './professional/professional.component';

import {
  HeaderModule
} from './shared/header/components/header.module';

import {
  LayoutComponent
} from './shared/layout/layout.component';

import {
  NutritionalDataComponent
} from './shared/nutrional-data/nutritional-data.component';

import {
  SidebarComponent
} from './shared/sidebar/sidebar.component';

import {
  SnackActionComponent
} from './snack/snack-action/snack-action.component';

import {
  SnackDeleteComponent
} from './snack/snack-delete/snack-delete.component';

import {
  SnackComponent
} from './snack/snack.component';

import {
  TableActionComponent
} from './table/table-action/table-action.component';

import {
  TableDeleteComponent
} from './table/table-delete/table-delete.component';

import {
  TableComponent
} from './table/table.component';

import {
  AttendanceComponent
} from './treatment/attendance/attendance.component';

import {
  FoodAttendanceComponent
} from './treatment/attendance/food-attendance/food-attendance.component';

import {
  CalendarProfessionalComponent
} from './treatment/calendar-professional/calendar-professional.component';

import {
  TreatmentComponent
} from './treatment/treatment.component';


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule,
        /*
         * O CalendarModule.forRoot() já está no AppModule.
         * Aqui importamos apenas o módulo.
         */
        CalendarModule,
        /*
         * ngx-translate 18
         */
        TranslatePipe,
        TranslateDirective,
        /*
         * ngx-mask 22
         */
        NgxMaskDirective,
        NgxMaskPipe,
        FontAwesomeModule,
        FlexLayoutModule,
        MatAutocompleteModule,
        MatBadgeModule,
        MatButtonModule,
        MatButtonToggleModule,
        MatCardModule,
        MatCheckboxModule,
        MatChipsModule,
        MatDatepickerModule,
        MatDialogModule,
        MatExpansionModule,
        MatFormFieldModule,
        MatGridListModule,
        MatIconModule,
        MatInputModule,
        MatListModule,
        MatMenuModule,
        MatNativeDateModule,
        MatPaginatorModule,
        MatRadioModule,
        MatSelectModule,
        MatSidenavModule,
        MatSlideToggleModule,
        MatSnackBarModule,
        MatTableModule,
        MatTabsModule,
        /*
         * Substitui ngx-mat-timepicker
         * e ngx-material-timepicker.
         */
        MatTimepickerModule,
        MatToolbarModule,
        MatTooltipModule,
        HeaderModule,
        AuthModule,
        LoaderModule,
        NotFoundComponent,
        FoodsComponent,
        FoodComponent,
        SidebarComponent,
        LayoutComponent,
        NutritionalDataComponent,
        FoodGroupComponent,
        FoodGroupAssociationComponent,
        PersonComponent,
        PersonActionComponent,
        PersonDeleteComponent,
        ProfessionalComponent,
        ProfessionalActionComponent,
        ProfessionalDeleteComponent,
        HomeComponent,
        PhoneDetailComponent,
        RemoveDialogComponent,
        CalendarComponent,
        CalendaractionComponent,
        TimeCalendarComponent,
        CancelCalendarComponent,
        CalendarProfessionalComponent,
        CalendarDraggableComponent,
        CalendarListComponent,
        CalendarFormComponent,
        AttendanceComponent,
        FoodAttendanceComponent,
        TreatmentComponent,
        PermissionComponent,
        TableComponent,
        TableActionComponent,
        TableDeleteComponent,
        SnackComponent,
        SnackActionComponent,
        SnackDeleteComponent,
    ],
    exports: [
        SidebarComponent,
        LayoutComponent,
        HeaderModule,
        AuthModule,
        LoaderModule,
    ],
})
export class ComponentsModule {}