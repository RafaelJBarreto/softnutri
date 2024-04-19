import * as _ from 'lodash';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Snack } from 'src/app/model/snack/snack';
import { FoodMenu } from 'src/app/model/snackMenu/foodMenu';
import { SnackMenu } from 'src/app/model/snackMenu/snackMenu';
import { Table } from 'src/app/model/table/table';
import { FoodBunchService } from 'src/app/services/foodBunch/foodBunch.service';
import { SnackService } from 'src/app/services/snack/snack.service';
import { TableService } from 'src/app/services/table/table.service';

import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatOption } from '@angular/material/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';

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
  listBreakFast!: Array<FoodMenu>;
  step = 0;
  loadingFoods: boolean = false;
  snacks: Snack[] = [];
  listSnackMenu!: Array<SnackMenu>;
  @Input() foodMenu!: FoodMenu;
  caloriesSum: number = 0;
  proteinSum: number = 0;
  carbohydrateSum: number = 0;
  lipidsSum: number = 0;

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
    this.tableService.listAll().then(
      (data) => {
        this.tables = data;
        this.filteredOptionsTable = this.tableControl.valueChanges
          .pipe(
            startWith(''),
            map((name) => name ? this.filterTable(name) : this.tables.slice())
          );
      })
      .catch((error) => {
        this.errorMessage = error.message;
        this.snackBar.open(this.translate.instant(error.error.message), 'Error', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000

        });
      })
    };
    
  private listSnack() {
    this.snackService.listAll().then(
      (data) => {
        this.snacks = data;
      }).catch((error) => {
        this.errorMessage = error.message;
        this.snackBar.open(this.translate.instant('SNACK.ERROR_LIST_SNACK'), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000

        });
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
              this.listSnackMenu[sm].listBreakFast.push(new FoodMenu(this.listSnackMenu[sm].listBreakFast.length + 1, idSnackMenu, 0, 0, 0, 0, 0, 0));
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
        this.listSnackMenu[sm].listBreakFast.push(new FoodMenu(this.listSnackMenu[sm].listBreakFast.length + 1, idSnackMenu, 0, 0, 0, 0, 0, 0));
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

  deleteFoodMenu(food: FoodMenu) {
    for(let sm = 0; sm < this.listSnackMenu.length; sm++){
      if(food.idSnackMenu === this.listSnackMenu[sm].idSnackMenu){

        const indexOfObject = this.listSnackMenu[sm].listBreakFast.findIndex((o: { idSnackMenu: number; }) => {
          return o.idSnackMenu === food.idSnackMenu;
        });
    
        if (indexOfObject !== -1) {
          this.listSnackMenu[sm].listBreakFast.splice(indexOfObject, 1);
        }
        
      }
    }
    this.setTotalFoodMenu();
  }

  totalFoodMenu(food: FoodMenu) {
    for(let sm = 0; sm < this.listSnackMenu.length; sm++){
      if(food.idSnackMenu === this.listSnackMenu[sm].idSnackMenu){
        for(let f = 0; f <  this.listSnackMenu[sm].listBreakFast.length; f++){
          if(food.id === this.listSnackMenu[sm].listBreakFast[f].id){
            this.listSnackMenu[sm].listBreakFast[f].idFood = food.idFood;
            this.listSnackMenu[sm].listBreakFast[f].amount = food.amount;
            this.listSnackMenu[sm].listBreakFast[f].calories = food.calories;
            this.listSnackMenu[sm].listBreakFast[f].protein = food.protein;
            this.listSnackMenu[sm].listBreakFast[f].lipids = food.lipids;
            this.listSnackMenu[sm].listBreakFast[f].carbohydrate = food.carbohydrate;
            break;
          }
        }
      }
    }

    this.setTotalFoodMenu();
  }

  setTotalFoodMenu() {
    this.resetSum();
    debugger;
    for(let sm = 0; sm < this.listSnackMenu.length; sm++){
      this.listSnackMenu[sm].listBreakFast.forEach(a => this.caloriesSum += a.calories);
      this.listSnackMenu[sm].listBreakFast.forEach(b => this.proteinSum += b.protein);
      this.listSnackMenu[sm].listBreakFast.forEach(c => this.lipidsSum += c.lipids);
      this.listSnackMenu[sm].listBreakFast.forEach(d => this.carbohydrateSum += d.carbohydrate);
    }
  }

  resetSum(){
    this.caloriesSum = 0;
    this.proteinSum = 0;
    this.carbohydrateSum = 0;
    this.lipidsSum = 0;
  }

  
}

