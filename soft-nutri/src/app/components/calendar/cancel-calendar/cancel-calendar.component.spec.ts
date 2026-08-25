import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelCalendarComponent } from './cancel-calendar.component';

describe('CancelCalendarComponent', () => {
  let component: CancelCalendarComponent;
  let fixture: ComponentFixture<CancelCalendarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
    imports: [CancelCalendarComponent]
})
    .compileComponents();

    fixture = TestBed.createComponent(CancelCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
