import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { BunchService } from 'src/app/services/bunch/bunch.service';
import { FoodService } from 'src/app/services/food/food.service';
import { FoodBunchService } from 'src/app/services/foodBunch/foodBunch.service';

@Component({
  selector: 'app-remove-dialog',
  templateUrl: './remove-dialog.component.html',
  styleUrls: ['./remove-dialog.component.scss']
})
export class RemoveDialogComponent{
 
  message: String = "";

  constructor(
    @Optional() public dialogRef: MatDialogRef<RemoveDialogComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private foodBunchService: FoodBunchService,
    private bunchService: BunchService,
    private foodService: FoodService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {
    if(this.data.valueForm === 'foodbunch'){
      this.message = translate.instant('BUNCH.DESCRIPTION_REMOVE_FOODBUNCH');
    }else if(this.data.valueForm === 'bunch'){
      this.message = translate.instant('BUNCH.DESCRIPTION_REMOVE_BUNCH');
    }else if(this.data.valueForm === 'food'){
      this.message = translate.instant('FOOD.DESCRIPTION_REMOVE');
    }
  }

  onClickDelete(): void {
    if(this.data.valueForm === 'foodbunch'){
      this. removeFoodBunch(this.data.id);
    }else if(this.data.valueForm === 'bunch'){
      this. removeBunch(this.data.id);
    }else if(this.data.valueForm === 'food'){
      this. removeFood(this.data.id);
    }
  }

  private removeFoodBunch(id: number): void{
    this.foodBunchService.delete(id).subscribe({
      next: data => {
        this.snackBar.open(this.translate.instant(data.message), '', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000
        });
      },
      error: err => {
        this.snackBar.open(this.translate.instant('PATIENT.ERROR_DELETE_PATIENT'), 'Error', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000
        });
      }
    });
    this.dialogRef.close();
  }

  private removeBunch(id: number): void{
    this.bunchService.delete(id).subscribe({
      next: data => {
        this.snackBar.open(this.translate.instant(data.message), '', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000
        });
      },
      error: err => {
        this.snackBar.open(this.translate.instant('PATIENT.ERROR_DELETE_PATIENT'), 'Error', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000
        });
      }
    });
    this.dialogRef.close();
  }

  private removeFood(id: number): void{
    this.foodService.delete(id).subscribe({
      next: data => {
        this.snackBar.open(this.translate.instant(data.message), '', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000
        });
      },
      error: err => {
        this.snackBar.open(this.translate.instant('PATIENT.ERROR_DELETE_PATIENT'), 'Error', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000
        });
      }
    });
    this.dialogRef.close();
  }


}
