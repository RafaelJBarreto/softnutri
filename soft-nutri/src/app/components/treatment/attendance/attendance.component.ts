import { Component, OnInit} from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { Table } from 'src/app/model/table/table';
import { MatOption } from '@angular/material/core';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { map, startWith } from 'rxjs/operators';
import { FoodBunchService } from 'src/app/services/foodBunch/foodBunch.service';
import { TableService } from 'src/app/services/table/table.service';
import { SnackService } from 'src/app/services/snack/snack.service';
import { Snack } from 'src/app/model/snack/snak';
import { SnackMenu } from 'src/app/model/snackMenu/snakMenu';
import * as _ from 'lodash';


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
  listBreakFast!: Array<number>;
  step = 0;
  loadingFoods: boolean = false;
  snacks: Snack[] = [];
  listSnackMenu!: Array<SnackMenu>;

  constructor(
    public snackService: SnackService,
    public foodBunchService: FoodBunchService,
    public tableService: TableService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    this.listSnack();
    this.listTable();
    this.listBreakFast = new Array
    this.listSnackMenu = new Array;
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
          duration: 3000

        });
      }
    });
  }

  private listSnack() {
    this.snackService.listAll().subscribe({
      next: data => {
        this.snacks = data;
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

  public selectedSnack(idSnack: number, idSnackMenu: number) {
    for(let sm = 0; sm < this.listSnackMenu.length; sm++){
      if(idSnackMenu === this.listSnackMenu[sm].idSnackMenu){
        for(let s = 0; s < this.listSnackMenu[sm].listSnackMenu.length; s++){
          if(idSnack === this.listSnackMenu[sm].listSnackMenu[s].idSnack){
            this.listSnackMenu[sm].listSnackMenu[s].selected = this.listSnackMenu[sm].listSnackMenu[s].selected ? false : true;
            if(this.listSnackMenu[sm].listSnackMenu[s].selected && this.listSnackMenu[sm].listBreakFast.length == 0){
              this.listSnackMenu[sm].listBreakFast.push(0);
            }
          }else{
            this.listSnackMenu[sm].listSnackMenu[s].selected = false;
          }
        }
      }
    }
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


  setFoodBreakFast(idSnackMenu: number) {
    for(let sm = 0; sm < this.listSnackMenu.length; sm++){
      if(idSnackMenu === this.listSnackMenu[sm].idSnackMenu){
        this.listSnackMenu[sm].listBreakFast.push(0);
      }
    }
  }

  setSnackMenu() {
    if(this.snacks.length > this.listSnackMenu.length){
      this.listSnackMenu.push(this.setValueObject());
    }else{
      this.snackBar.open(this.translate.instant('MENU.ERROR_SNACK_MENU'), '', {
        horizontalPosition: 'center',
        verticalPosition: 'top',
        duration: 3000

      });
    }
  }

  private setValueObject(){
    let sm:SnackMenu = {
      idSnackMenu: this.listSnackMenu.length + 1,
      listSnackMenu: _.cloneDeep(this.snacks),
      listBreakFast: _.cloneDeep(this.listBreakFast)
    };

    return sm;
  }

  removeSnackMenu(idSnackMenu: number) {
    const indexOfObject = this.listSnackMenu.findIndex((object) => {
      return object.idSnackMenu === idSnackMenu;
    });

    if (indexOfObject !== -1) {
      this.listSnackMenu.splice(indexOfObject, 1);
    }

  }

  
}

