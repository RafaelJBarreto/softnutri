import * as moment from 'moment';
import { Calendar } from 'src/app/model/calendar/calendar';
import { CalendarService } from 'src/app/services/calendar/calendar.service';
import { ConstService } from 'src/app/services/shared/const.service';

import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

import { CancelCalendarComponent } from '../cancel-calendar/cancel-calendar.component';
import { TimeCalendarComponent } from '../time-calendar/time-calendar.component';

@Component({
  selector: 'app-calendar-list',
  templateUrl: './calendar-list.component.html',
  styleUrl: './calendar-list.component.scss',
})
export class CalendarListComponent implements OnInit {
  public action: any;
  displayedColumns: string[] = [
    'professional',
    'patient',
    'dateOfDay',
    'hourOfDay',
    'note',
    'completed',
    'cancel',
    'actions',
  ];
  dataSource!: MatTableDataSource<Calendar>;
  calendar: Calendar[] = [];
  errorMessage: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    public dialog: MatDialog,
    public service: CalendarService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global: ConstService,
    private router: Router
  ) {
    this.action = this.global.rest.calendar.calendaraction;
  }

  ngOnInit(): void {
    this.listData();
  }

  private listData() {
    this.service.listAll().subscribe({
      next: (data) => {
        this.calendar = data.calendar;
        this.dataSource = new MatTableDataSource(this.calendar);
        this.dataSource.paginator = this.paginator;
        this.dataSource.filterPredicate = this.customFilterPredicate.bind(this);
      },
      error: (err) => {
        this.errorMessage = err.message;
        this.snackBar.open(this.translate.instant(err.error.message), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000,
        });
      },
    });
  }

  private customFilterPredicate(data: Calendar, filter: string): boolean {
    return (
      data.professional.name.toLowerCase().includes(filter) ||
      String(data.patient.name).toLowerCase().includes(filter) ||
      String(data.note).toLowerCase().includes(filter) ||
      String(moment(data.dateOfDay, 'YYYY-MM-DD').format('DD/MM/YYYY'))
        .toLowerCase()
        .includes(filter) ||
      String(data.hourOfDay).toLowerCase().includes(filter)
    );
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(TimeCalendarComponent, {
      width: '300px',
    });

    dialogRef.afterClosed().subscribe((_result) => {});
  }

  cancelCalendar(idCalendar: any) {
    const dialogRef = this.dialog.open(CancelCalendarComponent, {
      width: '250px',
      data: { id: idCalendar },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.listData();
    });
  }

  edit(idCalendar: any) {
    this.router.navigate([this.action, idCalendar]);
  }
}