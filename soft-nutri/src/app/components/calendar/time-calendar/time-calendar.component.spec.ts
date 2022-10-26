import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimeCalendarComponent } from './time-calendar.component';

describe('TimeCalendarComponent', () => {
  let component: TimeCalendarComponent;
  let fixture: ComponentFixture<TimeCalendarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TimeCalendarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TimeCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
