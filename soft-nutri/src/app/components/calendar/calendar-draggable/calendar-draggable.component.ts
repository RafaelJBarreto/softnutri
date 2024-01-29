import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormControl, UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/user/user';
import { PersonService } from 'src/app/services';
import { ConstService } from 'src/app/services/shared/const.service';
import { map, startWith } from 'rxjs/operators';
import { Calendar } from 'src/app/model/calendar/calendar';
import { ProfessionalService } from 'src/app/services/professional/professional.service';
import { CalendarService } from 'src/app/services/calendar/calendar.service';
import { MatOption } from '@angular/material/core';
import * as moment from 'moment';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CalendarEvent } from 'angular-calendar';

@Component({
  selector: 'app-calendar-draggable',
  templateUrl: './calendar-draggable.component.html',
  styleUrl: './calendar-draggable.component.scss'
})
export class CalendarDraggableComponent {

  public back: any;
  public form!: UntypedFormGroup;
  user: User = new User;
  errorMessage: any;
  isEdit!: boolean;
  patient: User[] = [];
  nutritionist: User[] = [];
  calendar: Calendar = new Calendar();
  patientControl = new FormControl();
  filteredOptionsPatient: Observable<User[]> = new Observable;
  nutritionistControl = new FormControl();
  filteredOptionsNutritionist: Observable<User[]>  = new Observable;
  professional: User = new User;
  patientSelected: User = new User;
  time: any;

  constructor(
    @Optional() public dialogRef: MatDialogRef<CalendarDraggableComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public event: any,
    public service: PersonService,
    public serviceCalendar: CalendarService,
    public professionalService: ProfessionalService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    this.getCalendar();
    this.listPatient();
    this.listNutritionist();
    this.form = new UntypedFormGroup({
      idCalendar: new UntypedFormControl(''),
      cancel: new UntypedFormControl(''),
      hourofday: new UntypedFormControl('',  [Validators.required]),
      dateofday: new UntypedFormControl('',  [Validators.required]),
      note: new UntypedFormControl('')

    });
  }

  onProfessionalSelected(option: MatOption) {
    this.professional = option.value;
  }

  onPatientSelected(option: MatOption) {
    this.patientSelected = option.value;
  }

  autoCompleteDisplayProfessional(item: any): string {
    if (item == undefined || item == "") { 
      return '';
    }
    return item.name;
  }

  autoCompleteDisplayPatient(item: any): string {
    if (item == undefined || item == "") { 
      return '';
    }
    return item.name;
  }

  public send(): void {
    if (this.form.valid) {
      this.setObject();
      this.serviceCalendar.save(this.calendar).subscribe({
        next: data => {
          this.snackBar.open(this.translate.instant(data.message), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000
          });

          this.dialogRef.close();
        },
        error: err => {
          this.errorMessage = err.message;
          this.snackBar.open(this.translate.instant('PATIENT.ERROR_SAVE_PATIENT'), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000
          });
        }
      });
    } else {
      this.snackBar.open(this.translate.instant('GLOBAL.ERROR_FORM'), '', {
        horizontalPosition: 'center',
        verticalPosition: 'top',
        duration: 3000
      });
    }
  }
  public getCalendar(): void {
    debugger;
      this.serviceCalendar.get(this.event.calendarEvent.id).subscribe({
        next: data => {
          this.calendar = data;
          this.validaForm();
        },
        error: err => {
          this.errorMessage = err.message;
          this.snackBar.open(this.translate.instant('PATIENT.ERROR_DADO_PATIENT'), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000
          });
        }
      });
  }

  public validaForm(): void {
    this.form.controls['idCalendar'].setValue(this.calendar.idCalendar);
      this.form.controls['dateofday'].setValue(this.event.calendarEvent.start);
      console.log(moment(this.event.calendarEvent.start, "DD/MM/YYYY hh:mm:ss").format("HH:mm"));
      this.form.controls['hourofday'].setValue(moment(this.event.calendarEvent.start, "DD/MM/YYYY hh:mm:ss").format("HH:mm"));
      this.form.controls['note'].setValue(this.calendar.note);
      this.form.controls['cancel'].setValue(this.calendar.cancel);
      this.patientSelected = this.calendar.patient;
      this.professional = this.calendar.professional;
      this.patientControl.setValue(this.patientSelected);
      this.nutritionistControl.setValue(this.calendar.professional);
  }

  public setObject(): void{
    this.calendar.idCalendar = this.form.controls['idCalendar'].value;
    this.calendar.professional = this.professional;
    this.calendar.patient = this.patientSelected;
    this.calendar.dateOfDay = this.form.controls['dateofday'].value; 
    let hourOfDay = moment(this.form.controls['hourofday'].value,'HH:mm').toDate();
    var userTimezoneOffset = hourOfDay.getTimezoneOffset() * 60000;
    this.calendar.hourOfDayAux =  new Date(hourOfDay.getTime() - userTimezoneOffset);
    this.calendar.note = this.form.controls['note'].value; 
    this.calendar.cancel = this.form.controls['cancel'].value; 
  }

  private listPatient() {
    this.service.listAll().subscribe({
      next: data => {
        this.patient = data;
        this.filteredOptionsPatient = this.patientControl.valueChanges
          .pipe(
            startWith(''),
            map(name => name ? this.filterPatient(name) : this.patient.slice())
          );
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open(this.translate.instant('PATIENT.ERROR_LIST_PATIENT'), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000

        });
      }
    });
  }


  private listNutritionist() {
    this.professionalService.getNutritionist().subscribe({
      next: data => {
        this.nutritionist = data;
        this.filteredOptionsNutritionist = this.nutritionistControl.valueChanges
          .pipe(
            startWith(''),
            map(name => name ? this.filterNutritionist(name) : this.nutritionist.slice())
          );
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open(this.translate.instant('PATIENT.ERROR_LIST_PATIENT'), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000

        });
      }
    });
  }

  private filterPatient(value: any) {
    let filterValue = '';
    if (typeof value === "string") {
      filterValue = value.toLowerCase();
    } else {
      filterValue = value.name.toLowerCase();
    }

    return this.patient.filter(
      option => option.name.toLowerCase().indexOf(filterValue) === 0
    );
  }

  private filterNutritionist(value: any) {
    let filterValue = '';
    if (typeof value === "string") {
      filterValue = value.toLowerCase();
    } else {
      filterValue = value.name.toLowerCase();
    }

    return this.nutritionist.filter(
      option => option.name.toLowerCase().indexOf(filterValue) === 0
    );
  }
}
