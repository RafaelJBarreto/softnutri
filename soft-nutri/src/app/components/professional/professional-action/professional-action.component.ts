import { Phone } from 'src/app/model/phone/phone';
import { User } from 'src/app/model/user/user';
import { ProfessionalService } from 'src/app/services/professional/professional.service';
import { ConstService } from 'src/app/services/shared/const.service';

import { Component, OnInit } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-professional-action',
  templateUrl: './professional-action.component.html',
  styleUrls: ['./professional-action.component.scss']
})
export class ProfessionalActionComponent implements OnInit {

  public back: any;
  public form!: UntypedFormGroup;
  user: User = new User;  
  errorMessage: any; 
  isEdit!: boolean;
  isNutritinist!: boolean;
  
  constructor(  
    private activatedroute:ActivatedRoute,
    public service:ProfessionalService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
    private global:ConstService,
    private router: Router
  ) {
    this.back = this.global.rest.professional.professional
  }

  ngOnInit(): void {
    this.validaForm(false);
    this.edit();
  }

  public send(): void {
    if (this.form.valid) { 
      this.setObject();
      this.service.save(this.user).then(
        (data) => {  
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
        }).catch((err) => { 
          this.errorMessage = err.message; 
          this.snackBar.open(this.translate.instant(err.error.message), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000
          });
      });
    }else{
      this.errorForm();
    }
  } 
  public edit(): void {
      this.activatedroute.paramMap.subscribe(params => { 
        let id = params.get('id');
        if(id === null){
          return;
        }
        this.service.get(id).then(
          (data) => {  
            this.user = data;
            this.validaForm(true);
          }).catch((err) => { 
            this.errorMessage = err.message; 
            this.snackBar.open(this.translate.instant(err.error.message), '', {
              horizontalPosition: 'center',
              verticalPosition: 'top',
              duration: 3000
            });
        });
    });
  }

  public validaForm(isEdit: boolean): void{
    this.isEdit = isEdit;
    if(!isEdit){
      this.form = new UntypedFormGroup({
        idPerson: new UntypedFormControl(''),
        name: new UntypedFormControl('', [Validators.required]),
        email: new UntypedFormControl('', [Validators.required,  Validators.email]),
        cpf: new UntypedFormControl('', [Validators.required]),
        address: new UntypedFormControl('', [Validators.required]),
        birthDate: new UntypedFormControl('', [Validators.required]),
        gender: new UntypedFormControl('', [Validators.required]),
        idPhone: new UntypedFormControl(''),
        celular: new UntypedFormControl(''),
        idPhone2: new UntypedFormControl(''),
        celular2: new UntypedFormControl(''),
        profession: new UntypedFormControl('', [Validators.required]),
        crn: new UntypedFormControl(''),
        language: new UntypedFormControl('', [Validators.required]),
        password: new UntypedFormControl('', [Validators.required]),

      }); 
    }else{
      this.form.controls['idPerson'].setValue(this.user.idPerson);
      this.form.controls['name'].setValue(this.user.name);
      this.form.controls['email'].setValue(this.user.email);
      this.form.controls['cpf'].setValue(this.user.cpf);
      this.form.controls['address'].setValue(this.user.address);
      this.form.controls['birthDate'].setValue(this.user.birthDate);
      this.form.controls['gender'].setValue(this.user.gender);
      for(let i = 0; i < this.user.phones.length; i++){
        if(i == 0){
          this.form.controls['idPhone'].setValue(this.user.phones[i].idPhone);
          this.form.controls['celular'].setValue(this.user.phones[i].numero);
        }else{
          this.form.controls['idPhone2'].setValue(this.user.phones[i].idPhone);
          this.form.controls['celular2'].setValue(this.user.phones[i].numero);
        }
      }
      this.form.controls['password'].setValue(this.user.password);
      this.form.controls['profession'].setValue(this.user.userType);
      this.form.controls['language'].setValue(this.user.language);
      this.form.controls['crn'].setValue(this.user.crn);
    }
  }

  public setObject(): void{
    this.user.idPerson = this.form.controls['idPerson'].value;
    this.user.name = this.form.controls['name'].value;
    this.user.email = this.form.controls['email'].value; 
    this.user.cpf = this.form.controls['cpf'].value; 
    this.user.address = this.form.controls['address'].value; 
    this.user.birthDate = this.form.controls['birthDate'].value; 
    this.user.gender = this.form.controls['gender'].value; 
    this.user.userType = this.form.controls['profession'].value;;
    let phones = [];
    if(this.form.controls['celular'].value != ""){
      phones.push(new Phone(this.form.controls['idPhone'].value, this.form.controls['celular'].value));
    }
    if(this.form.controls['celular2'].value != ""){
      phones.push(new Phone(this.form.controls['idPhone2'].value, this.form.controls['celular2'].value));
    }
    this.user.phones = phones;
    this.user.password = this.form.controls['password'].value; 
    this.user.language = this.form.controls['language'].value; 
    this.user.crn = this.form.controls['crn'].value; 
  }

  public isProfessionNutritinist(): void{
    this.isNutritinist = this.form.controls['profession'].value === "NUTRITIONIST";
    if(this.isNutritinist){
      this.form.controls["crn"].setValidators(Validators.required);
    }else{
      this.form.controls["crn"].setValidators(null);
    }
    this.form.controls["crn"].setValue(null);
  }

  public clearForm(): void{
    this.form.reset();
  }

  public errorForm(): void{
    this.snackBar.open(this.translate.instant('GLOBAL.ERROR_FORM'), '', {
      horizontalPosition: 'center',
      verticalPosition: 'top',
      duration: 3000
    });
  }
}
