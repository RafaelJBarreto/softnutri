import { AfterViewInit, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormControl, UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { map, Observable, startWith } from 'rxjs';
import { Food, FoodBunch } from 'src/app/model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FoodBunchService } from 'src/app/services/foodBunch/foodBunch.service';
import { MatOption } from '@angular/material/core';
import { Table } from 'src/app/model/table/table';

@Component({
  selector: 'app-food-attendance',
  templateUrl: './food-attendance.component.html',
  styleUrls: ['./food-attendance.component.scss']
})
export class FoodAttendanceComponent implements OnInit, OnChanges {

  public form!: UntypedFormGroup;
  foodControl = new FormControl();
  filteredOptionsFood: Observable<FoodBunch[]>  = new Observable;
  foods: FoodBunch[] = [];
  foodSelected: FoodBunch = new FoodBunch;
  @Input() idTable!: number;

  constructor(
    public service: FoodBunchService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.setForm();
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes['idTable'].currentValue != undefined){
      this.listFood(changes['idTable'].currentValue);
    }
  }

  setForm(): void{
    this.form = new UntypedFormGroup({
      amount: new UntypedFormControl('', [Validators.required]),
      calories: new UntypedFormControl('',  [Validators.required]),
      protein: new UntypedFormControl('',  [Validators.required]),
      lipids: new UntypedFormControl('', [Validators.required]),
      carbohydrate: new UntypedFormControl('', [Validators.required])
    });

    this.form.controls['calories'].disable();
    this.form.controls['protein'].disable();
    this.form.controls['lipids'].disable();
    this.form.controls['carbohydrate'].disable();
  }


  onFoodSelected(option: MatOption) {
    this.foodSelected = option.value;
    this.form.controls['calories'].setValue(option.value.food.nutritionalData.calories);
    this.form.controls['protein'].setValue(option.value.food.nutritionalData.protein);
    this.form.controls['lipids'].setValue(option.value.food.nutritionalData.lipids);
    this.form.controls['carbohydrate'].setValue(option.value.food.nutritionalData.carbohydrate);
  }

  setCalories(): void{
    debugger;
    let amount = this.form.controls['amount'].value;
    if(amount == null){
       this.form.controls['calories'].setValue(this.foodSelected.food.nutritionalData.calories);
       this.form.controls['protein'].setValue(this.foodSelected.food.nutritionalData.protein);
       this.form.controls['lipids'].setValue(this.foodSelected.food.nutritionalData.lipids);
       this.form.controls['carbohydrate'].setValue(this.foodSelected.food.nutritionalData.carbohydrate);
    }else{
      this.form.controls['calories'].setValue(this.toFixed((amount * this.foodSelected.food.nutritionalData.calories / 100), 2));
      this.form.controls['protein'].setValue(this.toFixed((amount * this.foodSelected.food.nutritionalData.protein / 100), 2));
      this.form.controls['lipids'].setValue(this.toFixed((amount * this.foodSelected.food.nutritionalData.lipids / 100), 2));
      this.form.controls['carbohydrate'].setValue(this.toFixed((amount * this.foodSelected.food.nutritionalData.carbohydrate / 100), 2));
    }
  }

  toFixed(num: number, fixed: number): number {
    fixed = fixed || 0;
    fixed = Math.pow(10, fixed);
    return Math.floor(num * fixed) / fixed;
}

  autoCompleteDisplayFood(item: any): string {
    if (item == undefined || item == "") { 
      return '';
    }
    return item.food.description;
  }

  private listFood(idTable: number) {
    this.service.getFoodTable(idTable).subscribe({
      next: data => {
        this.foods = data;
        this.filteredOptionsFood = this.foodControl.valueChanges
        .pipe(
          startWith(''),
          map(name => name ? this.filterFood(name) : this.foods.slice())
        )
      },
      error: err => {
        this.snackBar.open(this.translate.instant('FOOD.ERROR_LIST_FOOD'), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000

        });
      }
    });
  }

  private filterFood(value: any) {
    let filterValue = '';
    if (typeof value === "string") {
      filterValue = value.toLowerCase();
    } else {
      filterValue = value.food.description.toLowerCase();
    }

    return this.foods.filter(
      option => option.food.description.toLowerCase().indexOf(filterValue) === 0 || this.translate.instant(option.bunch.description).toLowerCase().indexOf(filterValue) === 0
    );
  }

}

