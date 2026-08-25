import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatDialog } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router, RouterModule } from '@angular/router';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { FlexLayoutModule } from '@ngbracket/ngx-layout';
import { Table } from 'src/app/model/table/table';
import { ConstService } from 'src/app/services/shared/const.service';
import { TableService } from 'src/app/services/table/table.service';

import { LayoutComponent } from '../shared/layout/layout.component';
import { TableDeleteComponent } from './table-delete/table-delete.component';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    LayoutComponent,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    MatToolbarModule,
    TranslatePipe,
  ],
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
})
export class TableComponent implements OnInit {
  public action: any;
  displayedColumns: string[] = ['name', 'description', 'actions'];
  dataSource!: MatTableDataSource<Table>;
  table: Table[] = [];
  errorMessage: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    public dialog: MatDialog,
    public service: TableService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global: ConstService,
    private router: Router
  ) {
    this.action = this.global.rest.table.tableaction;
  }

  ngOnInit(): void {
    this.listData();
  }

  private listData() {
    this.service
      .listAll()
      .then((data) => {
        this.table = data;
        this.dataSource = new MatTableDataSource(this.table);
        this.dataSource.paginator = this.paginator;
      })
      .catch((error) => {
        this.errorMessage = error.message;
        this.snackBar.open(
          this.translate.instant(error.error.message),
          'Error',
          {
            horizontalPosition: 'right',
            verticalPosition: 'top',
            duration: 3000,
          }
        );
      });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  edit(idCompositionTable: any) {
    this.router.navigate([this.action, idCompositionTable]);
  }

  delete(idCompositionTable: any) {
    const dialogRef = this.dialog.open(TableDeleteComponent, {
      width: '250px',
      data: { id: idCompositionTable },
    });

    dialogRef.afterClosed().subscribe(() => {
      this.listData();
    });
  }
}
