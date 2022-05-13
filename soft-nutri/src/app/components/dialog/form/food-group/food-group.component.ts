import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { FoodGroup } from 'src/app/model';
import { FoodService } from 'src/app/services/food/food.service';

@Component({
  selector: 'app-food-group',
  templateUrl: './food-group.component.html',
  styleUrls: ['./food-group.component.scss']
})
export class FoodGroupComponent implements OnInit {

  public form!: FormGroup;
  foodGroup: FoodGroup = new FoodGroup;  
  errorMessage: any; 

  
  constructor(  
    public dialogRef: MatDialogRef<FoodGroupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: FoodGroup,
    public service:FoodService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {}

  public ngOnInit(): void {
    this.form = new FormGroup({
      idFoodGroup: new FormControl(''),
      description: new FormControl('', [Validators.required]) 
    }); 
    this.verifyEdit();
  }

  verifyEdit(){
    if(this.data != null){
      this.form = new FormGroup({
        idFoodGroup: new FormControl(this.data.idFoodGroup),
        description: new FormControl(this.data.description, [Validators.required]) 
      });  
    }
  }

  public send(): void {
    console.log(this.form.controls);
    if (this.form.valid) { 
      this.foodGroup.description =this.form.controls['description'].value;
      this.foodGroup.idFoodGroup = (this.form.controls['idFoodGroup'].value != 0 ? this.form.controls['idFoodGroup'].value : null); 
      this.service.saveFoodGroup(this.foodGroup).subscribe({
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
 
}  
