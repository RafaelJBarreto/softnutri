import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Table } from 'src/app/model/table/table';
import { ConstService } from 'src/app/services/shared/const.service';
import { TableService } from 'src/app/services/table/table.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  public action: any;
  displayedColumns: string[] = ['name', 'description', "actions"];
  dataSource!: MatTableDataSource<Table>;
  table: Table[] = [];
  errorMessage: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(public dialog: MatDialog,
    public service: TableService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global: ConstService,
    private router: Router) {
    this.action = this.global.rest.table.tableaction;
  }

  ngOnInit(): void {
    this.listData();
  }

  private listData() {
    this.service.listAll().subscribe({
      next: data => {
        this.table = data;
        this.dataSource = new MatTableDataSource(this.table);
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open(this.translate.instant('TABLE.ERROR_LIST_TABLE'), '', {
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

  edit(idCompositionTable: any) {
    this.router.navigate([this.action, idCompositionTable]);
  }

}
