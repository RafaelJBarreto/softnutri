import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodAttendanceComponent } from './food-attendance.component';

describe('FoodAttendanceComponent', () => {
  let component: FoodAttendanceComponent;
  let fixture: ComponentFixture<FoodAttendanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FoodAttendanceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoodAttendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
