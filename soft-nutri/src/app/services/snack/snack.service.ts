import { Observable, firstValueFrom } from 'rxjs';
import { Snack } from 'src/app/model/snack/snack';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ConstService } from '../shared/const.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
@Injectable({
  providedIn: 'root',
})
export class SnackService {
  constructor(private http: HttpClient, private api: ConstService) {}

  listAll(): Promise<any> {
    return firstValueFrom(this.http.get(this.api.rest.snack.listall));
  }

  save(obj: Snack): Promise<any> {
    return firstValueFrom(this.http.post(this.api.rest.snack.save, obj));
  }

  get(idSnack: any): Promise<any> {
    return firstValueFrom(this.http.get(this.api.rest.snack.get + idSnack));
  }

  delete(idSnack: any): Promise<any> {
    return firstValueFrom(
      this.http.delete(this.api.rest.snack.delete + idSnack)
    );
  }
}
