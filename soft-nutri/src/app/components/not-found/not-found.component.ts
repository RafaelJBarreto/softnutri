import { Component, OnInit } from '@angular/core'; 
import { ConstService } from 'src/app/services/shared/const.service';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.scss']
})
export class NotFoundComponent{
  public rota: any;
  constructor( private global:ConstService
  ) {
    this.rota=this.global.redirect.HOME
  }
}
