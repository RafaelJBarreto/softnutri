import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NutritionalData } from 'src/app/model';

@Component({
  selector: 'app-nutritional-data',
  templateUrl: './nutritional-data.component.html',
  styleUrls: ['./nutritional-data.component.scss']
})
export class NutritionalDataComponent implements OnInit  {
  @Input() sendNutritionalData!: NutritionalData;
  @Output() sendNutritionalForm = new EventEmitter<NutritionalData>();
  public formNutritional!: FormGroup;
  nutritionalData!: NutritionalData; 
  changeLog: any;
   
  public ngOnInit(): void {
    this.formNutritional = new FormGroup({ 
      calories: new FormControl(0, [Validators.required]),
      protein: new FormControl(0, [Validators.required]),
      lipids: new FormControl(0, [Validators.required]),
      carbohydrate: new FormControl(0, [Validators.required])
    });
    this.verifyEdit();
  }

  verifyEdit(){
    if(this.sendNutritionalData.calories != null){
      this.formNutritional = new FormGroup({
        calories: new FormControl(this.nutritionalData.calories, [Validators.required]),
        protein: new FormControl(this.nutritionalData.protein, [Validators.required]),
        lipids: new FormControl(this.nutritionalData.lipids, [Validators.required]),
        carbohydrate: new FormControl(this.nutritionalData.carbohydrate, [Validators.required])
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
