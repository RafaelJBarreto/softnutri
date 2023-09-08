import { Component, EventEmitter, Inject, Input, OnInit, Output, ViewChild } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { NutritionalData } from 'src/app/model';
import { Food } from 'src/app/model/food/food';
import { FoodService } from 'src/app/services/food/food.service'; 
import { NutritionalDataComponent } from 'src/app/components/shared/nutrional-data/nutritional-data.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Table } from 'src/app/model/table/table';
import { TableService } from 'src/app/services/table/table.service';

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
  table: Table[] = [];
  @ViewChild(NutritionalDataComponent)
  private nutritionalDataComponent!: NutritionalDataComponent;
  
  constructor(  
    public dialogRef: MatDialogRef<FoodComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public service:FoodService,
    public serviceTable:TableService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {}


  public ngOnInit(): void {
    this.listTable();
    this.form = new UntypedFormGroup({
      idFood: new UntypedFormControl(''),
      description: new UntypedFormControl('', [Validators.required]) ,
      descriptionPreparation: new UntypedFormControl('', [Validators.required]),
      table: new UntypedFormControl('', [Validators.required]),
    }); 
    this.verifyEdit();
  }

  private listTable() {
    this.serviceTable.listAll().subscribe({
      next: data => {
        this.table = data;
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

  verifyEdit(){
    debugger;
    if(this.data != null){
      this.form.controls['idFood'].setValue(this.data.food.idFood);
      this.form.controls['description'].setValue(this.data.food.description);
      this.form.controls['descriptionPreparation'].setValue(this.data.food.descriptionPreparation);
      this.form.controls['table'].setValue(this.data.food.compositionTable.idCompositionTable);
      this.nutritionalData = this.data.food.nutritionalData
    }
  }

  public send(): void {
    if (this.form.valid) {
      this.nutritionalDataComponent.send();
      this.food.description =this.form.controls['description'].value;
      this.food.descriptionPreparation = this.form.controls['descriptionPreparation'].value;
      this.food.idFood = (this.form.controls['idFood'].value != 0 ? this.form.controls['idFood'].value : null); 
      this.food.compositionTable = this.getTable(this.form.controls['table'].value);
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

  public getTable(idCompositionTable: number){
    for(let i = 0; i < this.table.length; i++){
      if(this.table[i].idCompositionTable === idCompositionTable){
        return this.table[i];
      }
    }
    return new Table;
  }
} 

