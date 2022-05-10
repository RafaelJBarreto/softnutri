import { NgModule } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';
import { MatSidenavModule } from '@angular/material/sidenav';

import { SidebarComponent } from './sidebar/sidebar.component';
import { FooterComponent } from './footer/footer.component';
import { LayoutComponent } from './layout/layout.component';
import { HeaderModule } from './header/containers/header/header.module';

@NgModule({
  declarations: [
    SidebarComponent,
    FooterComponent,
    LayoutComponent
  ],
  imports: [
    MatListModule,
    MatIconModule,
    RouterModule,
    MatButtonModule,
    CommonModule,
    MatMenuModule,
    MatSelectModule,
    FormsModule,
    MatSidenavModule,
    HeaderModule
  ],
  exports: [
    SidebarComponent,
    HeaderModule,
    LayoutComponent
  ]
})
export class SharedModule { }
