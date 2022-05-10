import { Component, OnInit } from '@angular/core';
import { routes } from 'src/app/consts/routes';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.scss']
})
export class NotFoundComponent{

  public routes: typeof routes = routes;

}
