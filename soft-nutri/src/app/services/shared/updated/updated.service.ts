import { BehaviorSubject } from 'rxjs';

import { Injectable } from '@angular/core';

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