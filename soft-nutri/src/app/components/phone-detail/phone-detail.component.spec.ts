import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhoneDetailComponent } from './phone-detail.component';

describe('PhoneDetailComponent', () => {
  let component: PhoneDetailComponent;
  let fixture: ComponentFixture<PhoneDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PhoneDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PhoneDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
