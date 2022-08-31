import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { Food } from 'src/app/model';
import { FoodService } from 'src/app/services/food/food.service';
import { FoodComponent } from '../dialog/form/food/food.component';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { SelectionModel } from '@angular/cdk/collections';
import { FoodGroupComponent } from '../dialog/form/food-group/food-group.component';
import { DataFoodService } from 'src/app/services/food/dataFood.service';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { FoodBunchService } from 'src/app/services/foodBunch/foodBunch.service';

@Component({
  selector: 'app-foods',
  templateUrl: './foods.component.html',
  styleUrls: ['./foods.component.scss']
})
export class FoodsComponent implements OnInit {
  displayedColumns: string[] = ['select', 'bunch', 'description', 'calories', 'protein', 'lipids', 'carbohydrate'];
  dataSource!: MatTableDataSource<Food>;
  selection = new SelectionModel<Food>(true, []);
  food: Food[] = []; 
  errorMessage: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  selectedFoods!: Food;
  
  constructor(public dialog: MatDialog,
    public service: FoodService,
    public serviceFoodBunch: FoodBunchService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private dataFood: DataFoodService) { 
    }

  ngOnInit(): void {
    this.listData();
    this.dataFood.foodsData.subscribe(food=> this.selectedFoods);
  }

  private listData() {
    this.serviceFoodBunch.listAll().subscribe({
      next: data => {
        this.food = data;
        debugger;
        this.dataSource = new MatTableDataSource(this.food);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open('Erro ao cadastrar', '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000,
          panelClass: ['error']
          
        });
      }
    });
  }

  openDialog(number: number): void {
    if (number == 1) {
      const dialogRef = this.dialog.open(FoodComponent, {
        width: '500px'
      });

      dialogRef.afterClosed().subscribe(_result => {
        this.listData();
      });
    } else if (number == 2) {
      const dialogRef = this.dialog.open(FoodGroupComponent, {
        width: '300px'
      });

      dialogRef.afterClosed().subscribe(_result => { 
      });

    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    debugger;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
      const numSelected = this.selection.selected.length;
      const numRows = this.dataSource != undefined ? this.dataSource.data.length : 0;
      return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    if (this.isAllSelected()) {
      this.selection.clear();
      return;
    }
    this.selection.select(...this.dataSource.data);
  }

  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: Food): string {
    if (!row) {
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.idFood + 1}`;
  }

  public getSelected(event: MatCheckboxChange, row: Food): void{
    this.dataFood.updateFoodSelected(row, event.checked);
  }

}

