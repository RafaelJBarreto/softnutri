import { Component, EventEmitter, Inject, Input, OnInit, Output, ViewChild } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { NutritionalData } from 'src/app/model';
import { Food } from 'src/app/model/food/food';
import { throwIfEmpty } from 'rxjs';
import { FoodService } from 'src/app/services/food/food.service'; 
import { NutritionalDataComponent } from 'src/app/components/shared/nutrional-data/nutritional-data.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-food',
  templateUrl: './food.component.html',
  styleUrls: ['./food.component.scss']
})
export class FoodComponent implements OnInit { 
  public form!: UntypedFormGroup;
  food: Food = new Food; 
  nutritionalData:NutritionalData = new NutritionalData; 
  errorMessage: any;
  @ViewChild(NutritionalDataComponent)
  private nutritionalDataComponent!: NutritionalDataComponent;

  
  constructor(  
    public dialogRef: MatDialogRef<FoodComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Food,
    public service:FoodService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {}

  public ngOnInit(): void {
    this.form = new UntypedFormGroup({
      idFood: new UntypedFormControl(''),
      description: new UntypedFormControl('', [Validators.required]) 
    }); 
    this.verifyEdit();
  }

  verifyEdit(){
    if(this.data != null){
      this.form = new UntypedFormGroup({
        idFood: new UntypedFormControl(this.data.idFood),
        description: new UntypedFormControl(this.data.description, [Validators.required]) 
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
          this.snackBar.open(this.translate.instant(data.message), '', {
            horizontalPosition: 'right',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['success']
          });
          this.dialogRef.close();
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

  public updateNutritionalData(nutritionalData:NutritionalData):void{
    this.food.nutritionalData = nutritionalData;
  } 
} 

