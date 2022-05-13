import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Food } from 'src/app/model';
import { FoodComponent } from '../dialog/form/food/food.component';

@Component({
  selector: 'app-foods',
  templateUrl: './foods.component.html',
  styleUrls: ['./foods.component.scss']
})
export class FoodsComponent implements OnInit {

  food!:Food;
  constructor(public dialog: MatDialog) {}
  ngOnInit(): void {
    
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(FoodComponent, {
      width: '500px' 
    });

    dialogRef.afterClosed().subscribe(result => {
      this.food = result;
    });
  }

}
