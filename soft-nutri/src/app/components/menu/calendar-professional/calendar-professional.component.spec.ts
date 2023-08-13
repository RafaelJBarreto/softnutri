import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarProfessionalComponent } from './calendar-professional.component';

describe('CalendarProfessionalComponent', () => {
  let component: CalendarProfessionalComponent;
  let fixture: ComponentFixture<CalendarProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalendarProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CalendarProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
