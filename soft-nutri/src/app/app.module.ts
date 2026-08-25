import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';

import { registerLocaleData } from '@angular/common';
import {
  HTTP_INTERCEPTORS,
  provideHttpClient,
  withInterceptorsFromDi,
} from '@angular/common/http';

import localeES from '@angular/common/locales/es';
import localePT from '@angular/common/locales/pt';

import { NgModule } from '@angular/core';

import { MatPaginatorIntl } from '@angular/material/paginator';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { RouterModule } from '@angular/router';

import { JwtModule } from '@auth0/angular-jwt';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import {
  provideTranslateService,
  TranslateDirective,
  TranslatePipe,
  TranslateService,
} from '@ngx-translate/core';

import { provideTranslateHttpLoader } from '@ngx-translate/http-loader';

import { provideEnvironmentNgxMask } from 'ngx-mask';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ComponentsModule } from './components/components.module';

import { LoaderModule } from './components/loader/loader.module';
import { LoaderInterceptor } from './components/loader/loader.interceptor';

import { authInterceptorProviders } from './interceptor/auth.interceptor';

import { DataFoodService } from './services/food/dataFood.service';

import { ConstService } from './services/shared/const.service';

import { PaginatorI18n } from './services/shared/paginators/paginatorI18n';

import { Update } from './services/shared/updated/updated.service';

registerLocaleData(localePT);
registerLocaleData(localeES);

export function tokenGetter(): string | null {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [AppComponent],

  imports: [
    BrowserModule,

    BrowserAnimationsModule,

    RouterModule,

    AppRoutingModule,

    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),

    JwtModule.forRoot({
      config: {
        tokenGetter,
        allowedDomains: ['localhost:8080'],
        disallowedRoutes: [''],
      },
    }),

    FontAwesomeModule,

    /*
     * ngx-translate 18:
     * TranslateModule não existe mais.
     *
     * Pipe e directive agora são standalone.
     *
     * Estes imports atendem ao AppComponent.
     * ComponentsModule também deverá importar
     * TranslatePipe/TranslateDirective para os componentes dele.
     */
    TranslatePipe,
    TranslateDirective,

    ComponentsModule,

    LoaderModule,
  ],

  providers: [
    /*
     * HttpClient moderno.
     *
     * withInterceptorsFromDi() é necessário porque
     * ainda estamos usando interceptors baseados em
     * HTTP_INTERCEPTORS.
     */
    provideHttpClient(withInterceptorsFromDi()),

    /*
     * ngx-translate 18
     */
    provideTranslateService({
      loader: provideTranslateHttpLoader({
        prefix: './assets/i18n/',
        suffix: '.json',
      }),

      fallbackLang: 'pt-Br',
      lang: 'pt-Br',
    }),

    /*
     * ngx-mask 22
     */
    provideEnvironmentNgxMask({
      validation: false,
    }),

    authInterceptorProviders,

    ConstService,

    DataFoodService,

    Update,

    {
      provide: MatPaginatorIntl,
      deps: [TranslateService],

      useFactory: (translateService: TranslateService) =>
        new PaginatorI18n(translateService).getPaginatorIntl(),
    },

    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoaderInterceptor,
      multi: true,
    },
  ],

  bootstrap: [AppComponent],
})
export class AppModule {}
