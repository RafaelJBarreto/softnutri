import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.scss']
})
export class AttendanceComponent implements OnInit {

  listBreakFast: Array<number> = new Array;
  step = 0;

  constructor() { }

  ngOnInit(): void {
    this.listBreakFast.push(0);
  }

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }

  setFoodBreakFast() {
    this.listBreakFast.push(0);
  }
 

}
