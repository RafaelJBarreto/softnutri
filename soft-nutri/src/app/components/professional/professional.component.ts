import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Phone } from 'src/app/model/phone/phone';
import { User } from 'src/app/model/user/user';
import { ProfessionalService } from 'src/app/services/professional/professional.service';
import { ConstService } from 'src/app/services/shared/const.service';
import { PhoneDetailComponent } from '../phone-detail/phone-detail.component';
import { ProfessionalDeleteComponent } from './professional-delete/professional-delete.component';

@Component({
  selector: 'app-professional',
  templateUrl: './professional.component.html',
  styleUrls: ['./professional.component.scss']
})

export class ProfessionalComponent implements OnInit {

  public action: any;
  displayedColumns: string[] = ['name', 'email', 'cpf', 'birthDate', 'address', 'profession', "actions"];
  dataSource!: MatTableDataSource<User>;
  person: User[] = [];
  errorMessage: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(public dialog: MatDialog,
    public service: ProfessionalService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global: ConstService,
    private router: Router) {
    this.action = this.global.rest.professional.professionalaction;
  }

  ngOnInit(): void {
    this.listData();
  }

  private listData() {
    this.service.listAll().subscribe({
      next: data => {
        this.person = data;
        this.dataSource = new MatTableDataSource(this.person);
        this.dataSource.paginator = this.paginator;
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open(this.translate.instant('PROFESSIONAL.ERROR_LIST_PROFESSIONAL'), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000

        });
      }
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  delete(idPerson: any) {
    const dialogRef = this.dialog.open(ProfessionalDeleteComponent, {
      width: '250px',
      data: { id: idPerson },
    });

    dialogRef.afterClosed().subscribe(result => {
      this.listData();
    });
  }

  edit(idPerson: any) {
    this.router.navigate([this.action, idPerson]);
  }

  getPhone(phones: Array<Phone>) {
    const dialogRef = this.dialog.open(PhoneDetailComponent, {
      width: '300px',
      data: phones,
    });
  }


}
