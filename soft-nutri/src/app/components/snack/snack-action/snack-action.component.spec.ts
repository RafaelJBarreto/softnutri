import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SnackActionComponent } from './snack-action.component';

describe('SnackActionComponent', () => {
  let component: SnackActionComponent;
  let fixture: ComponentFixture<SnackActionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SnackActionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SnackActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
