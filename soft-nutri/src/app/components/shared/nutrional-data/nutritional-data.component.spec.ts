import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NutritionalDataComponent } from './nutritional-data.component';

describe('NutritionalDataComponent', () => {
  let component: NutritionalDataComponent;
  let fixture: ComponentFixture<NutritionalDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NutritionalDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NutritionalDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
