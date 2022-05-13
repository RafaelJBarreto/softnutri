import { Component, EventEmitter, Inject, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { NutritionalData } from 'src/app/model';
import { Food } from 'src/app/model/food/food';
import { throwIfEmpty } from 'rxjs';
import { FoodService } from 'src/app/services/food/food.service'; 
import { NutritionalDataComponent } from 'src/app/components/shared/nutrional-data/nutritional-data.component';

@Component({
  selector: 'app-food',
  templateUrl: './food.component.html',
  styleUrls: ['./food.component.scss']
})
export class FoodComponent implements OnInit { 
  public form!: FormGroup;
  food: Food = new Food; 
  nutritionalData:NutritionalData = new NutritionalData;
  snackBar: any;
  errorMessage: any;
  @ViewChild(NutritionalDataComponent)
  private nutritionalDataComponent!: NutritionalDataComponent;
  constructor(  
    public dialogRef: MatDialogRef<FoodComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Food,
    public service:FoodService,
    public translate: TranslateService
  ) {}

  public ngOnInit(): void {
    this.form = new FormGroup({
      idFood: new FormControl(''),
      description: new FormControl('', [Validators.required]) 
    }); 
    this.verifyEdit();
  }

  verifyEdit(){
    if(this.data != null){
      this.form = new FormGroup({
        idFood: new FormControl(this.data.idFood),
        description: new FormControl(this.data.description, [Validators.required]) 
      }); 
      this.nutritionalData = this.data.nutritionalData
    }
  }

  public send(): void {
    if (this.form.valid) {
      this.nutritionalDataComponent.send();
      this.food.description =this.form.controls['description'].value;
      this.food.idFood = (this.form.controls['idFood'].value != 0 ? this.form.controls['idFood'].value : null); 
      this.service.save(this.food).subscribe({
        next: data => {  
          this.snackBar.open(this.translate.instant(data), '', {
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
            duration: 10000
          });
          this.dialogRef.close();
        },
        error: err => { 
          this.errorMessage = err.message; 
          this.snackBar.open('Erro ao cadastrar', '', {
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
            duration: 10000
          });
        }
      });
    }
  } 

  public updateNutritionalData(nutritionalData:NutritionalData):void{
    this.food.nutritionalData = nutritionalData;
  } 
} 

