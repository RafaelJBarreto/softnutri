import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component'; 
import { AppRoutingModule } from './app-routing.module';
import { HttpClient } from '@angular/common/http';
import { authInterceptorProviders } from './interceptor/auth.interceptor';
import { JwtModule } from '@auth0/angular-jwt';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { ConstService } from './services/shared/const.service';
import { TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import { PaginatorI18n } from './services/shared/paginators/paginatorI18n';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { ComponentsModule } from './components/components.module';
import { NgxMaskModule, IConfig } from 'ngx-mask';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { DataFoodService } from './services/food/dataFood.service';
import { Update } from './services/shared/updated/updated.service';
import { BrowserModule } from '@angular/platform-browser';

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
    ComponentsModule,
    FontAwesomeModule,
  ],
  providers: [authInterceptorProviders, ConstService, DataFoodService, Update,
    {
      provide: MatPaginatorIntl, deps: [TranslateService],
      useFactory: (translateService: TranslateService) => new PaginatorI18n(translateService).getPaginatorIntl()
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }