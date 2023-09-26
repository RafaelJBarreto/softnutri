import { Component, Inject, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { PersonService } from 'src/app/services';
import { CalendarService } from 'src/app/services/calendar/calendar.service';

@Component({
  selector: 'app-cancel-calendar',
  templateUrl: './cancel-calendar.component.html',
  styleUrls: ['./cancel-calendar.component.scss']
})
export class CancelCalendarComponent {

  public action: any;

  constructor(
    @Optional() public dialogRef: MatDialogRef<CancelCalendarComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private service: CalendarService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {
  }

  onClickCancel(): void {
    this.service.cancel(this.data.id).subscribe({
      next: data => {
        this.snackBar.open(this.translate.instant(data.message), '', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000
        });
      },
      error: err => {
        this.snackBar.open(this.translate.instant('PATIENT.ERROR_DELETE_PATIENT'), 'Error', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000
        });
      }
    });
    this.dialogRef.close();
  }

}
