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
import { Router } from '@angular/router';

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
        for(let i = 0; i < data.length; i++){
          data[i].actions = this.actions;
          let date = startOfDay(data[i].start);
          data[i].start = addHours(date, data[i].end);
          data[i].end = addHours(data[i].start, 1),
          this.events.push(data[i]);
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


  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

}


