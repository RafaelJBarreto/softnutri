import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { Table } from 'src/app/model/table/table';
import { MatOption } from '@angular/material/core';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { map, startWith } from 'rxjs/operators';
import { FoodBunchService } from 'src/app/services/foodBunch/foodBunch.service';
import { FoodBunch } from 'src/app/model';
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
  tableSelected: Table = new Table;
  table: Table[] = [];
  @Input() foodListSend = new EventEmitter<FoodBunch[]>();
  food: FoodBunch[] = [];

  listBreakFast: Array<number> = new Array;
  step = 0;
  

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
        this.table = data;
        this.filteredOptionsTable = this.tableControl.valueChanges
          .pipe(
            startWith(''),
            map((name) => name ? this.filterTable(name) : this.table.slice())
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

    return this.table.filter(
      option => option.name.toLowerCase().indexOf(filterValue) === 0
    );
  }

  onTableSelected(option: MatOption) {
    this.tableSelected = option.value;
    this.listFood(option.value.idCompositionTable);
  }

  autoCompleteDisplayTable(item: any): string {
    if (item == undefined || item == "") { 
      return '';
    }
    return item.name;
  }

  private listFood(id: number) {
    this.foodBunchService.getFoodTable(id).subscribe({
      next: data => {
        this.food = data;
        debugger;
        this.foodListSend.emit(this.food);
      },
      error: err => {
        this.snackBar.open(this.translate.instant('FOOD.ERROR_LIST_FOOD'), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000,
          panelClass: ['error']

        });
      }
    });
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
