import { Component, OnInit } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Table } from 'src/app/model/table/table';
import { ConstService } from 'src/app/services/shared/const.service';
import { TableService } from 'src/app/services/table/table.service';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-table-action',
  templateUrl: './table-action.component.html',
  styleUrls: ['./table-action.component.scss']
})
export class TableActionComponent implements OnInit {

  public back: any;
  public form!: UntypedFormGroup;
  table: Table = new Table;  
  errorMessage: any; 
  isEdit!: boolean;

  constructor(  
    private activatedroute:ActivatedRoute,
    public service:TableService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global:ConstService,
    private router: Router
  ) {
    this.back= this.global.rest.table.table
  }

  ngOnInit(): void {
    this.validaForm(false);
    this.edit();
  }

  public validaForm(isEdit: boolean): void{
    this.isEdit = isEdit;
    if(!isEdit){
      this.form = new UntypedFormGroup({
        idCompositionTable: new UntypedFormControl(''),
        name: new UntypedFormControl('', [Validators.required, Validators.maxLength(20)]),
        description: new UntypedFormControl('', [Validators.required, Validators.maxLength(100)]),

      }); 
    }else{
      this.form.controls['idCompositionTable'].setValue(this.table.idCompositionTable);
      this.form.controls['name'].setValue(this.table.name);
      this.form.controls['description'].setValue(this.table.description);
    }
  }

  public send(): void {
    if (this.form.valid) { 
      this.setObject();
      this.service.save(this.table).subscribe({
        next: data => {  
          this.snackBar.open(this.translate.instant(data.message), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['success']
          });
          if(this.isEdit){
            this.router.navigate([this.back]);
          }else{
            this.clearForm();
          }
        },
        error: err => { 
          this.errorMessage = err.message; 
          this.snackBar.open(this.translate.instant('TABLE.ERROR_SAVE_TABLE'), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['error']
          });
        }
      });
    }else{
      this.snackBar.open(this.translate.instant('GLOBAL.ERROR_FORM'), '', {
        horizontalPosition: 'center',
        verticalPosition: 'top',
        duration: 3000,
        panelClass: ['error']
      });
    }
  } 

  public setObject(): void{
    this.table.idCompositionTable = this.form.controls['idCompositionTable'].value;
    this.table.name = this.form.controls['name'].value;
    this.table.description = this.form.controls['description'].value; 
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
          this.table = data;
          this.validaForm(true);
        },
        error: err => { 
          this.errorMessage = err.message; 
          this.snackBar.open(this.translate.instant('TABLE.ERROR_DADO_TABLE'), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['error']
          });
        }
      });
  });
}

}
