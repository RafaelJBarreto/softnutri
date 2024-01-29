import { ModuleUser } from 'src/app/model/module/module';
import { ConstService } from 'src/app/services/shared/const.service';

import { Component } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent{

  modulesUser: ModuleUser[] = this.global.getModuleList();

  constructor(
    private global: ConstService
    ) { 
    }

}
