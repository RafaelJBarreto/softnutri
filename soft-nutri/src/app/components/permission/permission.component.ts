import { Component, OnInit } from '@angular/core';
import { FormControl, UntypedFormGroup } from '@angular/forms';
import { Observable} from 'rxjs';
import { User } from 'src/app/model/user/user';
import { map, startWith } from 'rxjs/operators';
import { ProfessionalService } from 'src/app/services/professional/professional.service';
import { TranslateService } from '@ngx-translate/core';
import { MatOption } from '@angular/material/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PermissionService } from 'src/app/services/permission/permission.service';
import { Permission } from 'src/app/model/permission/permission';
import { Paper } from 'src/app/model/permission/paper';
import { PersonPaper } from 'src/app/model/permission/personPaper';

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html',
  styleUrls: ['./permission.component.scss']
})
export class PermissionComponent implements OnInit {
  public form!: UntypedFormGroup;
  errorMessage: any;
  userControl = new FormControl();
  filteredOptionsUser: Observable<User[]>  = new Observable;
  userSelected: User = new User;
  user: User[] = [];
  permission: Permission[] = [];
  panelOpenState = false;
  checked!: boolean;
  checkedAll!: boolean;
  personPaper: PersonPaper = new PersonPaper;

  constructor(
    public professionalService: ProfessionalService,
    public permissionService: PermissionService,
    public translate: TranslateService,
    private snackBar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
    this.listUser();
    this.checked = false;
    this.checkedAll = false;
  }

  onUserSelected(option: MatOption) {
    this.userSelected = option.value;
    this.listPermissionUser();
  }

  autoCompleteDisplayUser(item: any): string {
    if (item == undefined || item == "") { 
      return '';
    }
    return item.name;
  }

  private listUser() {
    this.professionalService.listAll().subscribe({
      next: data => {
        this.user = data;
        this.filteredOptionsUser = this.userControl.valueChanges
          .pipe(
            startWith(''),
            map(name => name ? this.filterUser(name) : this.user.slice())
          );
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open(this.translate.instant('PERMISSION.LIST_USER'), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000,
          panelClass: ['error']

        });
      }
    });
  }

  private filterUser(value: any) {
    let filterValue = '';
    if (typeof value === "string") {
      filterValue = value.toLowerCase();
    } else {
      filterValue = value.name.toLowerCase();
    }

    return this.user.filter(
      option => option.name.toLowerCase().indexOf(filterValue) === 0
    );
  }

  save() {
    if(this.userControl.value != ""){
      this.personPaper.idPerson = this.userSelected.idPerson;
      this.personPaper.permission = this.permission;
      this.permissionService.save(this.personPaper).subscribe({
        next: data => {  
          this.snackBar.open(this.translate.instant(data.message), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['success']
          });
        },
        error: err => { 
          this.errorMessage = err.message; 
          this.snackBar.open(this.translate.instant('PERMISSION.ERROR_SAVE'), '', {
            horizontalPosition: 'center',
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['error']
          });
        }
      });
    }else{
      this.snackBar.open(this.translate.instant('PERMISSION.ERROR_USER'), '', {
        horizontalPosition: 'center',
        verticalPosition: 'top',
        duration: 3000,
        panelClass: ['error']
      });
    }
  }


  private listPermissionUser() {
    this.permissionService.get(this.userSelected.idPerson).subscribe({
      next: data => {
        debugger;
        this.permission = data;
      },
      error: err => {
        this.errorMessage = err.message;
        this.snackBar.open(this.translate.instant('PATIENT.ERROR_LIST_PATIENT'), '', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 3000,
          panelClass: ['error']

        });
      }
    });
  }

  updateAll() {
    debugger;
    this.checkedAll = !this.checkedAll;
    for(let i = 0; i < this.permission.length; i++){
      this.permission[i].checked = this.checkedAll;
      for(let j = 0; j < this.permission[i].paper.length; j++){
        if(this.permission[i].paper[j].get != -1){
          this.permission[i].paper[j].get = this.checkedAll ? 1 : 0;
        }
        if(this.permission[i].paper[j].post != -1){
          this.permission[i].paper[j].post = this.checkedAll ? 1 : 0;
        }
        if(this.permission[i].paper[j].put != -1){
          this.permission[i].paper[j].put = this.checkedAll ? 1 : 0;
        }
        if(this.permission[i].paper[j].delete != -1){
          this.permission[i].paper[j].delete = this.checkedAll ? 1 : 0;
        }
      }
    }
  }

  validAll(){
    let cont = 0;
    for(let i = 0; i < this.permission.length; i++){
        if(this.permission[i].checked){
          cont++;
        }
    }

    if(cont === this.permission.length){
      this.checkedAll = true;
    }else{
      this.checkedAll = false;
    }
  }

  updatePermission(option: Permission) {
    option.checked = !option.checked;
    if(!option.checked){
      this.checkedAll = false;
    }
    for(let i = 0; i < option.paper.length; i++){
      if(option.paper[i].get != -1){
        option.checked ? option.paper[i].get = 1 : option.paper[i].get = 0;
      }
      if(option.paper[i].post != -1){
        option.checked ? option.paper[i].post = 1 : option.paper[i].post = 0;
      }
      if(option.paper[i].put != -1){
        option.checked ? option.paper[i].put = 1 : option.paper[i].put = 0;
      }
      if(option.paper[i].delete != -1){
        option.checked ? option.paper[i].delete = 1 : option.paper[i].delete = 0;
      }
    }

    this.validAll();
  }

  updatePaper(per: Permission, option: Paper, value: number) {
    if(value == 1){
      option.get == 0 ? option.get = 1 : option.get = 0;
    }

    if(value == 2){
      option.post == 0 ? option.post = 1 : option.post = 0;
    }

    if(value == 3){
      option.put == 0 ? option.put = 1 : option.put = 0;
    }

    if(value == 4){
      option.delete == 0 ? option.delete = 1 : option.delete = 0;
    }

    if(option.get == 1 && option.post == 1 && option.put == 1 && option.delete == 1){
      per.checked = true;
    }else{
      per.checked = false;
    }

    this.validAll();
  }

}
