import { Bunch } from 'src/app/model';
import { BunchService } from 'src/app/services/bunch/bunch.service';
import { Update } from 'src/app/services/shared/updated/updated.service';

import { Component, Inject, OnInit } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-food-group',
  templateUrl: './food-group.component.html',
  styleUrls: ['./food-group.component.scss']
})
export class FoodGroupComponent implements OnInit {

  public form!: UntypedFormGroup;
  bunch: Bunch = new Bunch;  
  errorMessage: any; 
  reloadForm!: boolean;

  constructor(  
    public dialogRef: MatDialogRef<FoodGroupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public service:BunchService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private update: Update
  ) {}

  public ngOnInit(): void {
    this.form = new UntypedFormGroup({
      idBunch: new UntypedFormControl(''),
      description: new UntypedFormControl('', [Validators.required]) 
    }); 
    this.verifyEdit();
    this.update.alteracaoData.subscribe(reloadForm => this.reloadForm = reloadForm);
  }

  verifyEdit(){
    if(this.data != null){
      this.form = new UntypedFormGroup({
        idBunch: new UntypedFormControl(this.data.bunch.idBunch),
        description: new UntypedFormControl(this.data.bunch.description, [Validators.required]) 
      });  
    }
  }
  

  public send(): void {
    if (this.form.valid) { 
      this.bunch.description = this.form.controls['description'].value;
      this.bunch.idBunch = (this.form.controls['idBunch'].value != 0 ? this.form.controls['idBunch'].value : null); 
      this.service.saveBunch(this.bunch).subscribe({
        next: data => {  
          this.snackBar.open(this.translate.instant(data.message), '', {
            horizontalPosition: 'right',
            verticalPosition: 'top',
            duration: 3000
          });
          this.update.updatForm(true);
          this.dialogRef.close();
        },
        error: err => { 
          this.errorMessage = err.message; 
          this.snackBar.open('Erro ao cadastrar', '', {
            horizontalPosition: 'right',
            verticalPosition: 'top',
            duration: 3000
          });
        }
      });
    }
  } 
 
  
}  
