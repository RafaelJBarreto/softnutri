import * as moment from 'moment';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Calendar } from 'src/app/model/calendar/calendar';
import { User } from 'src/app/model/user/user';
import { PersonService } from 'src/app/services';
import { CalendarService } from 'src/app/services/calendar/calendar.service';
import { ProfessionalService } from 'src/app/services/professional/professional.service';
import { ConstService } from 'src/app/services/shared/const.service';

import { Component, OnInit } from '@angular/core';
import { FormControl, UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { MatOption } from '@angular/material/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-calendaraction',
  templateUrl: './calendaraction.component.html',
  styleUrls: ['./calendaraction.component.scss']
})
export class CalendaractionComponent implements OnInit {
  
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
    private activatedroute: ActivatedRoute,
    public service: PersonService,
    public serviceCalendar: CalendarService,
    public professionalService: ProfessionalService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global: ConstService,
    private router: Router
  ) {
    this.back = this.global.rest.calendar.calendar
  }

  ngOnInit(): void {
    this.validaForm(false);
    this.edit();
    this.listPatient();
    this.listNutritionist();
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
          if (this.isEdit) {
            this.router.navigate([this.back]);
          } else {
            this.clearForm();
          }
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
  public edit(): void {
    this.activatedroute.paramMap.subscribe(params => {
      let id = params.get('id');
      if (id === null) {
        return;
      }
      this.serviceCalendar.get(id).subscribe({
        next: data => {
          this.calendar = data;
          this.validaForm(true);
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
    });
  }

  public validaForm(isEdit: boolean): void {
    this.isEdit = isEdit;
    if (!isEdit) {
      this.form = new UntypedFormGroup({
        idCalendar: new UntypedFormControl(''),
        cancel: new UntypedFormControl(''),
        hourofday: new UntypedFormControl('',  [Validators.required]),
        dateofday: new UntypedFormControl('',  [Validators.required]),
        note: new UntypedFormControl('')

      });
    } else {
      this.form.controls['idCalendar'].setValue(this.calendar.idCalendar);
      this.form.controls['dateofday'].setValue(this.calendar.dateOfDay);
      this.form.controls['hourofday'].setValue(this.calendar.hourOfDay.toLocaleString().slice(0, -3));
      this.form.controls['note'].setValue(this.calendar.note);
      this.form.controls['cancel'].setValue(this.calendar.cancel);
      this.patientSelected = this.calendar.patient;
      this.professional = this.calendar.professional;
      this.patientControl.setValue(this.patientSelected);
      this.nutritionistControl.setValue(this.calendar.professional);
    }
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

  public clearForm(): void{
    this.form.reset();
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
