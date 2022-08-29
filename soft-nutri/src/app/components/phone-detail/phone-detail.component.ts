import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Phone } from 'src/app/model/phone/phone';

@Component({
  selector: 'app-phone-detail',
  templateUrl: './phone-detail.component.html',
  styleUrls: ['./phone-detail.component.scss']
})
export class PhoneDetailComponent{

  constructor(  @Optional() public dialogRef: MatDialogRef<PhoneDetailComponent>,
  @Optional() @Inject(MAT_DIALOG_DATA) public data: Array<Phone>) { }
}
