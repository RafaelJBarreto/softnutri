import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarDraggableComponent } from './calendar-draggable.component';

describe('CalendarDraggableComponent', () => {
  let component: CalendarDraggableComponent;
  let fixture: ComponentFixture<CalendarDraggableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CalendarDraggableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CalendarDraggableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
