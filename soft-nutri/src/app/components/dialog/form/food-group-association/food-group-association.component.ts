import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { FoodGroup } from 'src/app/model';
import { FoodService } from 'src/app/services/food/food.service';

@Component({
  selector: 'app-food-group-association',
  templateUrl: './food-group-association.component.html',
  styleUrls: ['./food-group-association.component.scss']
})
export class FoodGroupAssociationComponent implements OnInit {

  public form!: FormGroup;
  foodGroup: FoodGroup = new FoodGroup;  
  foodGroups: FoodGroup[] = [];
  errorMessage: any; 

  
  constructor(   
    public service:FoodService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {}

  public ngOnInit(): void {
    this.form = new FormGroup({
      idFoodGroup: new FormControl('', [Validators.required]) 
    });  
    this.service.listAllFoodGroup().subscribe({
      next: data => {  
        this.foodGroups = data;
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

  public send(): void { 
    if (this.form.valid) {  
      this.foodGroup.idFoodGroup = (this.form.controls['idFoodGroup'].value != 0 ? this.form.controls['idFoodGroup'].value : null); 
      this.service.listAllFoodGroup().subscribe({
        next: data => {  
          this.snackBar.open(this.translate.instant(data.message), '', {
            horizontalPosition: 'right',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['success']
          }); 
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
 
}  