import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendaractionComponent } from './calendaraction.component';

describe('CalendaractionComponent', () => {
  let component: CalendaractionComponent;
  let fixture: ComponentFixture<CalendaractionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalendaractionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CalendaractionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
