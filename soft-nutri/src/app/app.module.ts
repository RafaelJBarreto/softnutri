import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

import { AppComponent } from './app.component';
import { SharedModule } from './components/shared/shared.module';
import { AppRoutingModule } from './app-routing.module';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AuthModule } from './components/auth/auth.module';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { authInterceptorProviders } from './interceptor/auth.interceptor';
import { JwtModule } from '@auth0/angular-jwt';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { FoodsComponent } from './components/foods/foods.component';
import { ConstService } from './services/shared/const.service';

export function tokenGetter() {
  return localStorage.getItem("access_token");
}
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    FoodsComponent 
  ],
  imports: [
    BrowserModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ["localhost:8080"],
        disallowedRoutes: [""],
      },
    }), 
    SharedModule,
    AuthModule,
    BrowserAnimationsModule,
    RouterModule,
    AppRoutingModule,
    MatCardModule,
    MatButtonModule,
    HttpClientModule,
    MatSnackBarModule,
  ],
  providers: [authInterceptorProviders,ConstService],
  bootstrap: [AppComponent]
})
export class AppModule { }