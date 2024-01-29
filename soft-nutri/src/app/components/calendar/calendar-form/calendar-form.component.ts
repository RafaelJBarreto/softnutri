import {
    CalendarEvent, CalendarEventAction, CalendarEventTimesChangedEvent, CalendarView
} from 'angular-calendar';
import { addHours, isSameDay, isSameMonth, startOfDay } from 'date-fns';
import { Subject } from 'rxjs';
import { CalendarService } from 'src/app/services/calendar/calendar.service';
import { ConstService } from 'src/app/services/shared/const.service';

import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

import { CalendarDraggableComponent } from '../calendar-draggable/calendar-draggable.component';
import { CancelCalendarComponent } from '../cancel-calendar/cancel-calendar.component';
import { TimeCalendarComponent } from '../time-calendar/time-calendar.component';

@Component({
  selector: 'app-calendar-form',
  templateUrl: './calendar-form.component.html',
  styleUrl: './calendar-form.component.scss'
})
export class CalendarFormComponent implements OnInit {

  public action: any;
  events: CalendarEvent[]= [];
  errorMessage: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  constructor(
    public dialog: MatDialog,
    public service: CalendarService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global: ConstService,
    private router: Router) {
      this.action = this.global.rest.calendar.calendaraction;
  }

  ngOnInit(): void {
    this.listData();
  }

  private listData() {
    this.service.listAll().subscribe({
      next: data => {
        this.events = [];
        for(let i = 0; i < data.calendarEvent.length; i++){
          data.calendarEvent[i].actions = this.actions;
          let date = startOfDay(data.calendarEvent[i].start);
          data.calendarEvent[i].start = addHours(date, data.calendarEvent[i].end);
          data.calendarEvent[i].end = addHours(data.calendarEvent[i].start, 1),
          this.events.push(data.calendarEvent[i]);
        }
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open(this.translate.instant('CALENDAR.ERROR_LIST_CALENDAR'), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000

        });
      }
    });
  }

  
  openDialog(): void {
      const dialogRef = this.dialog.open(TimeCalendarComponent, {
        width: '300px'
      });

      dialogRef.afterClosed().subscribe(_result => {
      });
  }

  cancelCalendar(idCalendar: any) {
    const dialogRef = this.dialog.open(CancelCalendarComponent, {
      width: '250px',
      data: { id: idCalendar },
    });

    dialogRef.afterClosed().subscribe(result => {
      this.listData();
    });
  }

  edit(idCalendar: any) {
    this.router.navigate([this.action, idCalendar]);
  }

  actions: CalendarEventAction[] = [
    {
      label: '<i class="fa-solid fa-pen-to-square" style="color: white;"></i>',
      a11yLabel: 'Edit',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.edit(event.id);
      },
    },
    {
      label: '<i class="fa-solid fa-trash" style="color: white;"></i>',
      a11yLabel: 'Delete',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.cancelCalendar(event.id);
      },
    },
  ];

  refresh = new Subject<void>();

  activeDayIsOpen: boolean = true;

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  eventTimesChanged({
    event,
    newStart,
    newEnd,
  }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map((iEvent) => {
      if (iEvent === event) {
        event.start = newStart;
        event.end = newEnd
        this.handleEvent('Dropped or resized', event);
      }

      return iEvent;
    });
  }

  handleEvent(action: string, event: CalendarEvent): void {
    debugger;
    const dialogRef = this.dialog.open(CalendarDraggableComponent, {
      width: '800px',
      data: { calendarEvent: event },
    });

    dialogRef.afterClosed().subscribe(result => {
      this.listData();
    });
  }


  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

}
