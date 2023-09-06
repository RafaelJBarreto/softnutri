import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StepAttendanceComponent } from './step-attendance.component';

describe('StepAttendanceComponent', () => {
  let component: StepAttendanceComponent;
  let fixture: ComponentFixture<StepAttendanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StepAttendanceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StepAttendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
