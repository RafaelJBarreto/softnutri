import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { IConfig, NgxMaskModule } from 'ngx-mask';

/* Importando a configuração de algumas linguagens */
import { registerLocaleData } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import localeES from '@angular/common/locales/es';
import localePT from '@angular/common/locales/pt';
import { NgModule } from '@angular/core';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { JwtModule } from '@auth0/angular-jwt';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ComponentsModule } from './components/components.module';
import { authInterceptorProviders } from './interceptor/auth.interceptor';
import { DataFoodService } from './services/food/dataFood.service';
import { ConstService } from './services/shared/const.service';
import { PaginatorI18n } from './services/shared/paginators/paginatorI18n';
import { Update } from './services/shared/updated/updated.service';

registerLocaleData(localePT);
registerLocaleData(localeES);

const maskConfigFunction: () => Partial<IConfig> = () => {
  return {
    validation: false,
  };
};


export function tokenGetter() {
  return localStorage.getItem("access_token");
}
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
    TranslateModule.forRoot({
      loader: { provide: TranslateLoader, useFactory: HttpLoaderFactory, deps: [HttpClient] },
      isolate: true,
      defaultLanguage: 'pt-Br'
    }),
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ["localhost:8080"],
        disallowedRoutes: [""],
      },
    }), 
    NgxMaskModule.forRoot(maskConfigFunction),
    BrowserAnimationsModule,
    RouterModule,
    AppRoutingModule,
    FontAwesomeModule,
    ComponentsModule,
  ],
  providers: [authInterceptorProviders, ConstService, DataFoodService, Update,
    {
      provide: MatPaginatorIntl, deps: [TranslateService],
      useFactory: (translateService: TranslateService) => new PaginatorI18n(translateService).getPaginatorIntl()
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }