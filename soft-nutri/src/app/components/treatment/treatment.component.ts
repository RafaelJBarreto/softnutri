import { Component } from '@angular/core';

import { MatCardModule } from '@angular/material/card';
import { MatTabsModule } from '@angular/material/tabs';

import { TranslatePipe } from '@ngx-translate/core';

import { LayoutComponent } from '../shared/layout/layout.component';

import {
  CalendarProfessionalComponent
} from './calendar-professional/calendar-professional.component';

import {
  AttendanceComponent
} from './attendance/attendance.component';

@Component({
  selector: 'app-treatment',
  standalone: true,

  imports: [
    LayoutComponent,
    MatCardModule,
    MatTabsModule,
    TranslatePipe,
    CalendarProfessionalComponent,
    AttendanceComponent
  ],

  templateUrl: './treatment.component.html',
  styleUrls: ['./treatment.component.scss']
})
export class TreatmentComponent {}