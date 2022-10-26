import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { TranslateService } from '@ngx-translate/core';
import { Calendar } from 'src/app/model/calendar/calendar';
import { CalendarService } from 'src/app/services/calendar/calendar.service';
import { ConstService } from 'src/app/services/shared/const.service';
import { TimeCalendarComponent } from './time-calendar/time-calendar.component';

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

  constructor(public dialog: MatDialog,
    public service: CalendarService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global: ConstService) {
    this.action = this.global.rest.calendar.calendaraction;
  }

  ngOnInit(): void {
    this.listData();
  }

  private listData() {
    this.service.listAll().subscribe({
      next: data => {
        this.calendar = data;
        this.dataSource = new MatTableDataSource(this.calendar);
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open(this.translate.instant('CALENDAR.ERROR_LIST_CALENDAR'), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000,
          panelClass: ['error']

        });
      }
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  
  openDialog(): void {

      const dialogRef = this.dialog.open(TimeCalendarComponent, {
        width: '350px'
      });

      dialogRef.afterClosed().subscribe(_result => {
      });
  }
}


