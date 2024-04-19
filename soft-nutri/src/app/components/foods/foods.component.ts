import { Bunch, Food, FoodBunch } from 'src/app/model';
import { DataFoodService } from 'src/app/services/food/dataFood.service';
import { FoodService } from 'src/app/services/food/food.service';
import { FoodBunchService } from 'src/app/services/foodBunch/foodBunch.service';
import { Update } from 'src/app/services/shared/updated/updated.service';

import { SelectionModel } from '@angular/cdk/collections';
import {
  Component,
  OnChanges,
  OnInit,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TranslateService } from '@ngx-translate/core';

import { FoodGroupComponent } from '../dialog/form/food-group/food-group.component';
import { FoodComponent } from '../dialog/form/food/food.component';
import { RemoveDialogComponent } from '../dialog/form/remove-dialog/remove-dialog.component';

@Component({
  selector: 'app-foods',
  templateUrl: './foods.component.html',
  styleUrls: ['./foods.component.scss'],
})
export class FoodsComponent implements OnInit, OnChanges {
  displayedColumns: string[] = [
    'select',
    'bunch',
    'table',
    'description',
    'preparation',
    'calories',
    'protein',
    'lipids',
    'carbohydrate',
    'actionsFood',
  ];
  dataSource: MatTableDataSource<FoodBunch> = new MatTableDataSource();
  selection = new SelectionModel<FoodBunch>(true, []);
  food: FoodBunch[] = [];
  errorMessage: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  reloadForm!: boolean;

  constructor(
    public dialog: MatDialog,
    public service: FoodService,
    public serviceFoodBunch: FoodBunchService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private dataFood: DataFoodService,
    private update: Update
  ) {
    this.update.alteracaoData.subscribe(
      (reloadForm) => (this.reloadForm = reloadForm)
    );
  }
  ngOnChanges(changes: SimpleChanges): void {
    this.update.reloadForm;
    if (changes['name']) {
      console.log(changes['name']);
    }
  }

  ngOnInit(): void {
    this.listData();
  }

  public listData() {
    this.serviceFoodBunch.listAll().then(
      (data) => {
        this.food = data;
        this.dataSource = new MatTableDataSource(this.food);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.dataSource.filterPredicate = this.customFilterPredicate.bind(this);
        this.update.updatForm(true);
      }).catch((err) => {
        this.errorMessage = err.message;
        this.snackBar.open('Erro ao cadastrar', '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000,
        });
    });
  }

  openDialog(number: number): void {
    if (number == 1) {
      const dialogRef = this.dialog.open(FoodComponent, {
        width: '600px',
      });

      dialogRef.afterClosed().subscribe((_result) => {
        this.listData();
      });
    } else if (number == 2) {
      const dialogRef = this.dialog.open(FoodGroupComponent, {
        width: '450px',
      });

      dialogRef.afterClosed().subscribe((_result) => {});
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows =
      this.dataSource != undefined ? this.dataSource.data.length : 0;
    return numSelected === numRows;
  }

  masterToggle() {
    if (this.isAllSelected()) {
      this.selection.clear();
      return;
    }
    this.selection.select(...this.dataSource.data);
  }

  checkboxLabel(row?: FoodBunch): string {
    if (!row) {
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${
      row.food.idFood + 1
    }`;
  }

  public getSelected(event: MatCheckboxChange, row: Food): void {
    this.dataFood.updateFoodSelected(row, event.checked);
  }

  private customFilterPredicate(data: FoodBunch, filter: string): boolean {
    return (
      data.food.description.toLowerCase().includes(filter) ||
      String(data.bunch.description).toLowerCase().includes(filter) ||
      String(data.food.nutritionalData.calories)
        .toLowerCase()
        .includes(filter) ||
      String(data.food.nutritionalData.carbohydrate)
        .toLowerCase()
        .includes(filter) ||
      String(data.food.nutritionalData.lipids).toLowerCase().includes(filter) ||
      String(data.food.nutritionalData.protein)
        .toLowerCase()
        .includes(filter) ||
      String(data.food.descriptionPreparation).toLowerCase().includes(filter) ||
      String(data.food.compositionTable.name).toLowerCase().includes(filter)
    );
  }

  public removeFoodbunch(idFoodbunch: any): void {
    const dialogRef = this.dialog.open(RemoveDialogComponent, {
      width: '250px',
      data: { id: idFoodbunch, valueForm: 'foodbunch' },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.listData();
    });
  }

  public removeBunch(idBunch: any): void {
    const dialogRef = this.dialog.open(RemoveDialogComponent, {
      width: '250px',
      data: { id: idBunch, valueForm: 'bunch' },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.listData();
    });
  }

  public removeFood(idFood: any): void {
    const dialogRef = this.dialog.open(RemoveDialogComponent, {
      width: '250px',
      data: { id: idFood, valueForm: 'food' },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.listData();
    });
  }

  public editFood(food: Food): void {
    const dialogRef = this.dialog.open(FoodComponent, {
      width: '600px',
      data: { food },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.listData();
    });
  }

  public editBunch(bunch: Bunch): void {
    const dialogRef = this.dialog.open(FoodGroupComponent, {
      width: '400px',
      data: { bunch },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.listData();
    });
  }
}
