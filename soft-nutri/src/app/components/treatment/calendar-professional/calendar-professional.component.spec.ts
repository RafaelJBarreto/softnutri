import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarProfessionalComponent } from './calendar-professional.component';

describe('CalendarProfessionalComponent', () => {
  let component: CalendarProfessionalComponent;
  let fixture: ComponentFixture<CalendarProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
    imports: [CalendarProfessionalComponent]
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
