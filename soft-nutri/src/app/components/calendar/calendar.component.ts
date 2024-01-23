import { ChangeDetectionStrategy, Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { TranslateService } from '@ngx-translate/core';
import { Calendar } from 'src/app/model/calendar/calendar';
import { CalendarService } from 'src/app/services/calendar/calendar.service';
import { ConstService } from 'src/app/services/shared/const.service';
import { CancelCalendarComponent } from './cancel-calendar/cancel-calendar.component';
import { TimeCalendarComponent } from './time-calendar/time-calendar.component';
import { CalendarEvent, CalendarEventAction, CalendarEventTimesChangedEvent, CalendarView } from 'angular-calendar';
import { Subject } from 'rxjs';
import { addDays, addHours, endOfDay, endOfMonth, isSameDay, isSameMonth, startOfDay, subDays } from 'date-fns';
import { EventColor } from 'calendar-utils';
import { Router } from '@angular/router';

const colors: Record<string, EventColor> = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss']
})



export class CalendarComponent implements OnInit {

  public action: any;
  displayedColumns: string[] = ['professional', 'patient', 'dateOfDay', 'hourOfDay', 'note', 'completed', 'cancel', 'actions'];
  dataSource!: MatTableDataSource<Calendar>;
  calendar: Calendar[] = [];
  errorMessage: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate!:  Date;


  constructor(
    public dialog: MatDialog,
    public service: CalendarService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global: ConstService,
    private router: Router) {
      debugger;
    this.action = this.global.rest.calendar.calendaraction;
    this.viewDate = new Date();
  }

  ngOnInit(): void {
    this.listData();
  }

  private listData() {
    this.service.listAll().subscribe({
      next: data => {
        this.calendar = data;
        this.dataSource = new MatTableDataSource(this.calendar);
        this.dataSource.paginator = this.paginator;
        this.dataSource.filterPredicate = this.customFilterPredicate.bind(this);
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

  private customFilterPredicate(data: Calendar, filter: string): boolean {
    return data.professional.name.toLowerCase().includes(filter)
      || String(data.patient.name).toLowerCase().includes(filter)
      || String(data.note).toLowerCase().includes(filter)
      //|| String(moment(data.dateOfDay, "YYYY-MM-DD").format("DD/MM/YYYY")).toLowerCase().includes(filter)
      || String(data.hourOfDay).toLowerCase().includes(filter);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
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
      label: '<i class="fas fa-fw fa-pencil-alt"></i>',
      a11yLabel: 'Edit',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.handleEvent('Edited', event);
      },
    },
    {
      label: '<i class="fas fa-fw fa-trash-alt"></i>',
      a11yLabel: 'Delete',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
        this.handleEvent('Deleted', event);
      },
    },
  ];

  refresh = new Subject<void>();

  events: CalendarEvent[] = [
    {
      start: subDays(startOfDay(new Date()), 1),
      end: addDays(new Date(), 1),
      title: 'A 3 day event',
      color: { ...colors['red'] },
      actions: this.actions,
      allDay: true,
      resizable: {
        beforeStart: true,
        afterEnd: true,
      },
      draggable: true,
    },
    {
      start: startOfDay(new Date()),
      title: 'An event with no end date',
      color: { ...colors['yellow'] },
      actions: this.actions,
    },
    {
      start: subDays(endOfMonth(new Date()), 3),
      end: addDays(endOfMonth(new Date()), 3),
      title: 'A long event that spans 2 months',
      color: { ...colors['blue'] },
      allDay: true,
    },
    {
      start: addHours(startOfDay(new Date()), 2),
      end: addHours(new Date(), 2),
      title: 'A draggable and resizable event',
      color: { ...colors['yellow'] },
      actions: this.actions,
      resizable: {
        beforeStart: true,
        afterEnd: true,
      },
      draggable: true,
    },
  ];

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
        return {
          ...event,
          start: newStart,
          end: newEnd,
        };
      }
      return iEvent;
    });
    this.handleEvent('Dropped or resized', event);
  }

  handleEvent(action: string, event: CalendarEvent): void {
    //this.modalData = { event, action };
   // this.modal.open(this.modalContent, { size: 'lg' });
  }

  addEvent(): void {
    this.events = [
      ...this.events,
      {
        title: 'New event',
        start: startOfDay(new Date()),
        end: endOfDay(new Date()),
        color: colors['red'],
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
  }

  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter((event) => event !== eventToDelete);
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

}


