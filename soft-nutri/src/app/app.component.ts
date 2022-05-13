import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { ConstService } from './services/shared/const.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'soft-nutri';
  
  constructor( 
    private router: Router, 
    public translate: TranslateService,
    private global: ConstService
  ) { }

  ngOnInit(): void {
  const browserLang = this.translate.getBrowserLang();
    if(this.global.getLanguageVar() != undefined && this.global.getLanguageVar() != "") {
      this.translate.use(this.global.getLanguageVar());
    } else if (browserLang != undefined){
      this.translate.use(browserLang.match(/pt-br|en/) ? browserLang : 'pt-br');
    }
  }
}
