import { Component, ViewEncapsulation } from '@angular/core';
import { Subject } from 'rxjs';
import { LoaderService } from './loader.service';

@Component({
  selector: 'app-spinner',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.scss'],
  encapsulation: ViewEncapsulation.ShadowDom
})
export class LoaderComponent {
  constructor(public loaderService: LoaderService) {}
}
