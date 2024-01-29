import { NutritionalData } from 'src/app/model';

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-nutritional-data',
  templateUrl: './nutritional-data.component.html',
  styleUrls: ['./nutritional-data.component.scss']
})
export class NutritionalDataComponent implements OnInit  {
  @Input() sendNutritionalData!: NutritionalData;
  @Output() sendNutritionalForm = new EventEmitter<NutritionalData>();
  public formNutritional!: UntypedFormGroup;
  nutritionalData!: NutritionalData; 
  changeLog: any;
   
  public ngOnInit(): void {
    this.formNutritional = new UntypedFormGroup({ 
      calories: new UntypedFormControl(0, [Validators.required]),
      protein: new UntypedFormControl(0, [Validators.required]),
      lipids: new UntypedFormControl(0, [Validators.required]),
      carbohydrate: new UntypedFormControl(0, [Validators.required])
    });
    this.verifyEdit();
  }

  verifyEdit(){
    if(this.sendNutritionalData.calories != null){
      this.formNutritional = new UntypedFormGroup({
        calories: new UntypedFormControl(this.sendNutritionalData.calories, [Validators.required]),
        protein: new UntypedFormControl(this.sendNutritionalData.protein, [Validators.required]),
        lipids: new UntypedFormControl(this.sendNutritionalData.lipids, [Validators.required]),
        carbohydrate: new UntypedFormControl(this.sendNutritionalData.carbohydrate, [Validators.required])
      });  
    }
  } 
  
  public send(): void { 
      this.nutritionalData = { 
          calories: this.formNutritional.controls['calories'].value,
          protein: this.formNutritional.controls['protein'].value,
          lipids: this.formNutritional.controls['lipids'].value,
          carbohydrate: this.formNutritional.controls['carbohydrate'].value  
      }
      this.sendNutritionalForm.emit(this.nutritionalData); 
  }
   
}
