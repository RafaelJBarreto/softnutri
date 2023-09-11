import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SnackDeleteComponent } from './snack-delete.component';

describe('SnackDeleteComponent', () => {
  let component: SnackDeleteComponent;
  let fixture: ComponentFixture<SnackDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SnackDeleteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SnackDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
