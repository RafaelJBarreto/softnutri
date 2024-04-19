import { NutritionalDataComponent } from 'src/app/components/shared/nutrional-data/nutritional-data.component';
import { NutritionalData } from 'src/app/model';
import { Food } from 'src/app/model/food/food';
import { Table } from 'src/app/model/table/table';
import { FoodService } from 'src/app/services/food/food.service';
import { TableService } from 'src/app/services/table/table.service';

import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import {
  UntypedFormControl,
  UntypedFormGroup,
  Validators,
} from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-food',
  templateUrl: './food.component.html',
  styleUrls: ['./food.component.scss'],
})
export class FoodComponent implements OnInit {
  public form!: UntypedFormGroup;
  food: Food = new Food();
  nutritionalData: NutritionalData = new NutritionalData();
  errorMessage: any;
  table: Table[] = [];
  @ViewChild(NutritionalDataComponent)
  private nutritionalDataComponent!: NutritionalDataComponent;

  constructor(
    public dialogRef: MatDialogRef<FoodComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public foodService: FoodService,
    public tableService: TableService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {}

  public ngOnInit(): void {
    this.listTable();
    this.form = new UntypedFormGroup({
      idFood: new UntypedFormControl(''),
      description: new UntypedFormControl('', [Validators.required]),
      descriptionPreparation: new UntypedFormControl('', [Validators.required]),
      table: new UntypedFormControl('', [Validators.required]),
    });
    this.verifyEdit();
  }

  private listTable() {
    this.tableService
      .listAll()
      .then((data) => {
        this.table = data;
      })
      .catch((error) => {
        this.errorMessage = error.message;
        this.snackBar.open(
          this.translate.instant(error.error.message),
          'Error',
          {
            horizontalPosition: 'right',
            verticalPosition: 'top',
            duration: 3000,
          }
        );
      });
  }

  verifyEdit() {
    if (this.data != null) {
      this.form.controls['idFood'].setValue(this.data.food.idFood);
      this.form.controls['description'].setValue(this.data.food.description);
      this.form.controls['descriptionPreparation'].setValue(
        this.data.food.descriptionPreparation
      );
      this.form.controls['table'].setValue(
        this.data.food.compositionTable.idCompositionTable
      );
      this.nutritionalData = this.data.food.nutritionalData;
    }
  }

  public send(): void {
    if (this.form.valid) {
      this.nutritionalDataComponent.send();
      this.food.description = this.form.controls['description'].value;
      this.food.descriptionPreparation =
        this.form.controls['descriptionPreparation'].value;
      this.food.idFood =
        this.form.controls['idFood'].value != 0
          ? this.form.controls['idFood'].value
          : null;
      this.food.compositionTable = this.getTable(
        this.form.controls['table'].value
      );
      this.foodService
        .save(this.food)
        .then((data) => {
          this.snackBar.open(this.translate.instant(data.message), '', {
            horizontalPosition: 'right',
            verticalPosition: 'top',
            duration: 3000,
          });
          this.dialogRef.close();
        })
        .catch((err) => {
          this.errorMessage = err.message;
          this.snackBar.open(
            this.translate.instant(err.error.message),
            'Error',
            {
              horizontalPosition: 'right',
              verticalPosition: 'top',
              duration: 3000,
            }
          );
        });
    }
  }

  public updateNutritionalData(nutritionalData: NutritionalData): void {
    this.food.nutritionalData = nutritionalData;
  }

  public getTable(idCompositionTable: number) {
    for (let i = 0; i < this.table.length; i++) {
      if (this.table[i].idCompositionTable === idCompositionTable) {
        return this.table[i];
      }
    }
    return new Table();
  }
}
