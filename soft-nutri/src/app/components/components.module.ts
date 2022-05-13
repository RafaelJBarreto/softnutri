import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FoodComponent } from './dialog/form/food/food.component';
import { FoodsComponent } from './foods/foods.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSidenavModule } from '@angular/material/sidenav';
import { HeaderModule } from './shared/header/containers/header/header.module'; 
import { AuthModule } from './auth/auth.module';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { LayoutComponent } from './shared/layout/layout.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component'; 
import { FooterComponent } from './shared/footer/footer.component';
import { TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core'; 
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { PaginatorI18n } from '../services/shared/paginators/paginatorI18n';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { NutritionalDataComponent } from './shared/nutrional-data/nutritional-data.component';
import { authInterceptorProviders } from '../interceptor/auth.interceptor';
import { ConstService } from '../services/shared/const.service';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatTableModule } from '@angular/material/table';
import { FoodGroupComponent } from './dialog/form/food-group/food-group.component';
import { FoodGroupAssociationComponent } from './dialog/form/food-group-association/food-group-association.component';


export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    NotFoundComponent,
    FoodsComponent,
    FoodComponent,
    SidebarComponent,
    FooterComponent,
    LayoutComponent,
    NutritionalDataComponent,
    FoodGroupComponent,
    FoodGroupAssociationComponent],
  imports: [
    MatListModule,
    TranslateModule.forRoot({
      loader: { provide: TranslateLoader, useFactory: HttpLoaderFactory, deps: [HttpClient] },
      isolate: true,
      defaultLanguage: 'en'
    }),
    MatIconModule,
    RouterModule,
    MatButtonModule,
    CommonModule,
    MatMenuModule,
    MatSelectModule,
    FormsModule,
    MatSidenavModule,
    HeaderModule,
    ReactiveFormsModule,
    MatCardModule,
    MatButtonModule,
    HttpClientModule,
    MatSnackBarModule,
    RouterModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatInputModule,
    MatPaginatorModule, 
    MatCheckboxModule,
    MatTableModule,
    AuthModule,
  ],
  exports: [
    SidebarComponent,
    HeaderModule,
    LayoutComponent
  ],
  providers:[ authInterceptorProviders,ConstService,
    {
      provide: MatPaginatorIntl, deps: [TranslateService],
      useFactory: (translateService: TranslateService) => new PaginatorI18n(translateService).getPaginatorIntl()
    }
  ]
})
export class ComponentsModule { }
