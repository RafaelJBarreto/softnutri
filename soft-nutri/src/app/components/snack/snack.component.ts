import { Snack } from 'src/app/model/snack/snack';
import { ConstService } from 'src/app/services/shared/const.service';
import { SnackService } from 'src/app/services/snack/snack.service';

import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

import { SnackDeleteComponent } from './snack-delete/snack-delete.component';

@Component({
  selector: 'app-snack',
  templateUrl: './snack.component.html',
  styleUrls: ['./snack.component.scss']
})
export class SnackComponent implements OnInit {

  public action: any;
  displayedColumns: string[] = ['name', 'description', "actions"];
  dataSource!: MatTableDataSource<Snack>;
  snack: Snack[] = [];
  errorMessage: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(public dialog: MatDialog,
    public service: SnackService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global: ConstService,
    private router: Router) {
    this.action = this.global.rest.snack.snackaction;
  }

  ngOnInit(): void {
    this.listData();
  }

  private listData() {
    this.service.listAll().subscribe({
      next: data => {
        this.snack = data;
        this.dataSource = new MatTableDataSource(this.snack);
        this.dataSource.paginator = this.paginator;
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open(this.translate.instant('SNACK.ERROR_LIST_SNACK'), '', {
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

  edit(idSnack: any) {
    this.router.navigate([this.action, idSnack]);
  }

  delete(idSnack: any) {
    const dialogRef = this.dialog.open(SnackDeleteComponent, {
      width: '250px',
      data: { id: idSnack },
    });

    dialogRef.afterClosed().subscribe(result => {
      this.listData();
    });
  }


}
