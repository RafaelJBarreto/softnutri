import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { FoodsComponent } from 'src/app/components/foods/foods.component';
import { Bunch, Food, FoodBunch } from 'src/app/model';
import { BunchService } from 'src/app/services/bunch/bunch.service';
import { DataFoodService } from 'src/app/services/food/dataFood.service';
import { FoodBunchService } from 'src/app/services/foodBunch/foodBunch.service';
import { Update } from 'src/app/services/shared/updated/updated.service';

@Component({
  selector: 'app-food-group-association',
  templateUrl: './food-group-association.component.html',
  styleUrls: ['./food-group-association.component.scss']
})
export class FoodGroupAssociationComponent implements OnInit {
  public form!: UntypedFormGroup;
  bunch: Bunch = new Bunch;  
  bunchs: Bunch[] = [];
  errorMessage: any; 
  selectedsFoods!: Array<Food>;
  selectedFoods!: Food;
  foodBunch: FoodBunch = new FoodBunch;  
  reloadForm: boolean = false;

  constructor(   
    public service:BunchService,
    public serviceFoodBunch:FoodBunchService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private dataFood: DataFoodService,
    private update: Update
  ) {
    this.update.alteracaoData.subscribe(reloadForm => this.reloadForm = reloadForm);
  }
 
  public ngOnInit(): void {
    this.form = new UntypedFormGroup({
      idFoodGroup: new UntypedFormControl('', [Validators.required]) 
    });  
    this.loadBunchs();
  } 

  public sendFoodBunch(): void { 
    this.getValuesFood();
    if( this.selectedsFoods.length == 0){
        this.snackBar.open(this.translate.instant('FOOD.ERROR_FOOD_EMPTY'), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000,
          panelClass: ['error']
        });
        return;
    }
    if (this.form.valid) {  
      this.bunch = (this.form.controls['idFoodGroup'].value != 0 ? this.form.controls['idFoodGroup'].value : null); 
      this.foodBunch.bunch = this.bunch;
      this.foodBunch.foods = this.selectedsFoods;
      this.serviceFoodBunch.saveFoodBunch(this.foodBunch).subscribe({
        next: data => {  
          this.snackBar.open(this.translate.instant(data.message), '', {
            horizontalPosition: 'right',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['success']
          });
          this.dataFood.reset();
          this.update.updatForm(true);
        },
        error: err => { 
          this.errorMessage = err.message; 
          this.snackBar.open(this.translate.instant('FOOD.ERROR_FOOD_BUNCH'), '', {
            horizontalPosition: 'right',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['error']
          });
        }
      });
    }
  } 

  public getValuesFood():void{
    this.selectedsFoods = this.dataFood.getSelectedFoods();
  }

  public getBunchs(): void{
    this.update.alteracaoData.subscribe(reloadForm => this.reloadForm = reloadForm);
    if(this.reloadForm){
      this.loadBunchs();
    }
  }

  private loadBunchs(): void{
    this.service.listAllBunch().subscribe({
      next: data => {  
        this.bunchs = data;
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
 
}  