import { AfterViewInit, Component,  EventEmitter,  Input,  OnInit,  Output,  ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { Table } from 'src/app/model/table/table';
import { MatOption } from '@angular/material/core';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { map, startWith } from 'rxjs/operators';
import { FoodBunchService } from 'src/app/services/foodBunch/foodBunch.service';
import { TableService } from 'src/app/services/table/table.service';


@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.scss']
})
export class AttendanceComponent implements OnInit {
  errorMessage: any;
  tableControl = new FormControl();
  filteredOptionsTable: Observable<Table[]>  = new Observable;
  table: Table = new Table;
  tables: Table[] = [];
  idTable!: number;
  listBreakFast: Array<number> = new Array;
  step = 0;
  loadingFoods: boolean = false;
  

  constructor(
    public foodBunchService: FoodBunchService,
    public tableService: TableService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
    this.listTable();
    this.listBreakFast.push(0);
  }

  private listTable() {
    this.tableService.listAll().subscribe({
      next: data => {
        this.tables = data;
        this.filteredOptionsTable = this.tableControl.valueChanges
          .pipe(
            startWith(''),
            map((name) => name ? this.filterTable(name) : this.tables.slice())
          );
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

  private filterTable(value: any) {
    let filterValue = '';
    if (typeof value === "string") {
      filterValue = value.toLowerCase();
    } else {
      filterValue = value.name.toLowerCase();
    }

    return this.tables.filter(
      option => option.name.toLowerCase().indexOf(filterValue) === 0
    );
  }

  onTableSelected(option: MatOption) {
    this.table = option.value;
    this.idTable = this.table.idCompositionTable;
    this.loadingFoods = true;
  }

  autoCompleteDisplayTable(item: any): string {
    if (item == undefined || item == "") { 
      return '';
    }
    return item.name;
  }

  getValue(id: Number) {
    console.log(id);
  }


  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }

  setFoodBreakFast() {
    this.listBreakFast.push(0);
  }
}
