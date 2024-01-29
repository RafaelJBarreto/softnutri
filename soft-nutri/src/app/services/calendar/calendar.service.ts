import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ConstService } from '../shared/const.service';
import { Calendar } from 'src/app/model/calendar/calendar';
import { CalendarSend} from 'src/app/model/calendar/calendarSend';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
@Injectable({
  providedIn: 'root'
})
export class CalendarService { 

  constructor(
    private http: HttpClient,
    private api:ConstService
  ) { }

  listAll(): Observable<any> { 
    return this.http.get(this.api.rest.calendar.listall);
  }

  save(obj:Calendar): Observable<any> {
    return this.http.post(this.api.rest.calendar.save, obj );
  } 

  get(idCalendar:any): Observable<any> {
    return this.http.get(this.api.rest.calendar.get + idCalendar );
  } 

  cancel(idCalendar:any): Observable<any> {
    return this.http.get(this.api.rest.calendar.cancel + idCalendar );
  } 

  listCalendarProfessional(): Observable<any> { 
    return this.http.get(this.api.rest.calendar.professional);
  }

}