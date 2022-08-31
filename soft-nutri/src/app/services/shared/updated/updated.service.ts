import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Food } from 'src/app/model';

@Injectable()
export class Update {

  private alteracao = new BehaviorSubject<boolean>(false);
  alteracaoData = this.alteracao.asObservable();
  reloadForm: boolean = false;

  constructor() { }

  public updatForm(reloadData: boolean) {
    this.alteracao.next(reloadData);
    this.reloadForm = reloadData;
  }


}