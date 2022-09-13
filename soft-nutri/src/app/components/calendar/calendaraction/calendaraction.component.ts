import { Component, OnInit } from '@angular/core';
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
import * as moment from 'moment';

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
  patientControl = new FormControl('');
  filteredOptionsPatient: Observable<User[]> = new Observable;
  nutritionistControl = new FormControl('');
  filteredOptionsNutritionist: Observable<User[]>  = new Observable;

  public date!: moment.Moment;
  public disabled = false;
  public showSpinners = true;
  public showSeconds = false;
  public touchUi = false;
  public enableMeridian = false;
  public minDate!: moment.Moment;
  public maxDate!: moment.Moment;
  public stepHour = 1;
  public stepMinute = 1;
  public stepSecond = 1;


  constructor(
    private activatedroute: ActivatedRoute,
    public service: PersonService,
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

  public send(): void {
    if (this.form.valid) {
      this.setObject();
      this.service.save(this.user).subscribe({
        next: data => {
          this.snackBar.open(this.translate.instant(data.message), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['success']
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
            duration: 3000,
            panelClass: ['error']
          });
        }
      });
    } else {
      this.snackBar.open(this.translate.instant('GLOBAL.ERROR_FORM'), '', {
        horizontalPosition: 'center',
        verticalPosition: 'top',
        duration: 3000,
        panelClass: ['error']
      });
    }
  }
  public edit(): void {
    this.activatedroute.paramMap.subscribe(params => {
      let id = params.get('id');
      if (id === null) {
        return;
      }
      this.service.get(id).subscribe({
        next: data => {
          this.user = data;
          this.validaForm(true);
        },
        error: err => {
          this.errorMessage = err.message;
          this.snackBar.open(this.translate.instant('PATIENT.ERROR_DADO_PATIENT'), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['error']
          });
        }
      });
    });
  }

  public validaForm(isEdit: boolean): void {
    this.isEdit = isEdit;
    if (!isEdit) {
      this.form = new UntypedFormGroup({
        idCalendar: new UntypedFormControl('')

      });
    } else {
      this.form.controls['idCalendar'].setValue(this.calendar.idCalendar);
      this.form.controls['patient'].setValue(this.calendar.patient);
    }
  }
  public setObject(): void {

  }

  public clearForm(): void {
    this.form.reset();
  }

  private listPatient() {
    this.service.listAll().subscribe({
      next: data => {
        this.patient = data;
        console.log(this.patient);
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
          duration: 3000,
          panelClass: ['error']

        });
      }
    });
  }

  private listNutritionist() {
    this.professionalService.getNutritionist().subscribe({
      next: data => {
        this.nutritionist = data;
        console.log(this.nutritionist);
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
          duration: 3000,
          panelClass: ['error']

        });
      }
    });
  }

  private filterPatient(value: any) {
    let filterValue = '';
    if (typeof value === "string") {
      filterValue = value.toLowerCase();
    } else {
      filterValue = value.description.toLowerCase();
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
      filterValue = value.description.toLowerCase();
    }

    return this.nutritionist.filter(
      option => option.name.toLowerCase().indexOf(filterValue) === 0
    );
  }

}
