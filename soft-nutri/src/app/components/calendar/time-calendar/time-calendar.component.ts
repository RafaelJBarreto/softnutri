import { Component, Inject, OnInit } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { Intermission } from 'src/app/model/Intermission/Intermission';
import { IntermissionService } from 'src/app/services/Intermission/Intermission.service';
import { Update } from 'src/app/services/shared/updated/updated.service';

@Component({
  selector: 'app-time-calendar',
  templateUrl: './time-calendar.component.html',
  styleUrls: ['./time-calendar.component.scss']
})
export class TimeCalendarComponent implements OnInit {

  public form!: UntypedFormGroup;
  interval: Intermission = new Intermission;  
  errorMessage: any; 
  reloadForm!: boolean;

  constructor(  
    public dialogRef: MatDialogRef<TimeCalendarComponent>,
    public service:IntermissionService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
  ) {}

  public ngOnInit(): void {
    this.form = new UntypedFormGroup({
      idIntermission: new UntypedFormControl(''),
      time: new UntypedFormControl('', [Validators.required]) 
    }); 
    this.getInterval();
  }

  verifyEdit(){
    if(this.interval != null){
      this.form = new UntypedFormGroup({
        idIntermission: new UntypedFormControl(this.interval.idIntermission),
        time: new UntypedFormControl(this.interval.time, [Validators.required]) 
      });  
    }
  }

  private getInterval() {
    this.service.get().subscribe({
      next: data => {
        debugger;
        this.interval = data;
        this.verifyEdit();
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open(this.translate.instant('PATIENT.ERROR_LIST_PATIENT'), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000,
          panelClass: ['error']

        });
      }
    });
  }
  

  public send(): void {
    debugger;
    if (this.form.valid) { 
      this.interval.time = this.form.controls['time'].value;
      this.interval.idIntermission = (this.form.controls['idIntermission'].value != 0 ? this.form.controls['idIntermission'].value : null); 
      this.service.save(this.interval).subscribe({
        next: data => {  
          this.snackBar.open(this.translate.instant(data.message), '', {
            horizontalPosition: 'right',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['success']
          });
          this.dialogRef.close();
        },
        error: err => { 
          this.errorMessage = err.message; 
          this.snackBar.open('Erro ao cadastrar', '', {
            horizontalPosition: 'right',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['error']
          });
        }
      });
    }
  } 

}
