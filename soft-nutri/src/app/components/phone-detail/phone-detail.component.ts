import { Phone } from 'src/app/model/phone/phone';

import { Component, Inject, Optional } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-phone-detail',
  templateUrl: './phone-detail.component.html',
  styleUrls: ['./phone-detail.component.scss']
})
export class PhoneDetailComponent{

  constructor(  @Optional() public dialogRef: MatDialogRef<PhoneDetailComponent>,
  @Optional() @Inject(MAT_DIALOG_DATA) public data: Array<Phone>) { }
}
