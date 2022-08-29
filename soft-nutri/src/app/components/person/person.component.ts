import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Person } from 'src/app/model/person/person';
import { Phone } from 'src/app/model/phone/phone';
import { PersonService } from 'src/app/services/patient/patient.service';
import { ConstService } from 'src/app/services/shared/const.service';
import { PhoneDetailComponent } from '../phone-detail/phone-detail.component';
import { PersonDeleteComponent } from './person-delete/person-delete.component';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.scss']
})
export class PersonComponent implements OnInit {
  public action: any;
  displayedColumns: string[] = ['idPatient', 'name', 'email', 'cpf', 'birthDate', 'address', "actions"];
  dataSource!: MatTableDataSource<Person>;
  person: Person[] = [];
  errorMessage: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(public dialog: MatDialog,
    public service: PersonService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global: ConstService,
    private router: Router) {
    this.action = this.global.rest.patient.patientaction;
  }

  ngOnInit(): void {
    this.listData();
  }

  private listData() {
    this.service.listAll().subscribe({
      next: data => {
        this.person = data;
        this.dataSource = new MatTableDataSource(this.person);
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

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  delete(idPerson: any) {
    const dialogRef = this.dialog.open(PersonDeleteComponent, {
      width: '250px',
      data: { id: idPerson },
    });

    dialogRef.afterClosed().subscribe(result => {
      this.listData();
    });
  }

  getPhone(phones: Array<Phone>) {
    const dialogRef = this.dialog.open(PhoneDetailComponent, {
      width: '300px',
      data: phones,
    });
  }

  edit(idPerson: any) {
    this.router.navigate([this.action, idPerson]);
  }

}
