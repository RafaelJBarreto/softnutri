import { Snack } from 'src/app/model/snack/snack';
import { ConstService } from 'src/app/services/shared/const.service';
import { SnackService } from 'src/app/services/snack/snack.service';

import { Component, OnInit } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-snack-action',
  templateUrl: './snack-action.component.html',
  styleUrls: ['./snack-action.component.scss']
})
export class SnackActionComponent implements OnInit {

  public back: any;
  public form!: UntypedFormGroup;
  snack: Snack = new Snack;  
  errorMessage: any; 
  isEdit!: boolean;

  constructor(  
    private activatedroute:ActivatedRoute,
    public service:SnackService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global:ConstService,
    private router: Router
  ) {
    this.back= this.global.rest.snack.snack
  }

  ngOnInit(): void {
    this.validaForm(false);
    this.edit();
  }

  public validaForm(isEdit: boolean): void{
    this.isEdit = isEdit;
    if(!isEdit){
      this.form = new UntypedFormGroup({
        idSnack: new UntypedFormControl(''),
        name: new UntypedFormControl('', [Validators.required, Validators.maxLength(30)]),
        description: new UntypedFormControl('', [Validators.required, Validators.maxLength(100)]),

      }); 
    }else{
      this.form.controls['idSnack'].setValue(this.snack.idSnack);
      this.form.controls['name'].setValue(this.snack.name);
      this.form.controls['description'].setValue(this.snack.description);
    }
  }

  public send(): void {
    if (this.form.valid) { 
      this.setObject();
      this.service.save(this.snack).subscribe({
        next: data => {  
          this.snackBar.open(this.translate.instant(data.message), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000
          });
          if(this.isEdit){
            this.router.navigate([this.back]);
          }else{
            this.clearForm();
          }
        },
        error: err => { 
          this.errorMessage = err.message; 
          this.snackBar.open(this.translate.instant('SNACK.ERROR_SAVE_SNACK'), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000
          });
        }
      });
    }else{
      this.snackBar.open(this.translate.instant('GLOBAL.ERROR_FORM'), '', {
        horizontalPosition: 'center',
        verticalPosition: 'top',
        duration: 3000
      });
    }
  } 

  public setObject(): void{
    this.snack.idSnack = this.form.controls['idSnack'].value;
    this.snack.name = this.form.controls['name'].value;
    this.snack.description = this.form.controls['description'].value; 
  }

  public clearForm(): void{
    this.form.reset();
  }

  public edit(): void {
    this.activatedroute.paramMap.subscribe(params => { 
      let id = params.get('id');
      if(id === null){
        return;
      }
      this.service.get(id).subscribe({
        next: data => {  
          this.snack = data;
          this.validaForm(true);
        },
        error: err => { 
          this.errorMessage = err.message; 
          this.snackBar.open(this.translate.instant('SNACK.ERROR_DADO_SNACK'), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000
          });
        }
      });
  });
}

}
