import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodGroupAssociationComponent } from './food-group-association.component';

describe('FoodGroupAssociationComponent', () => {
  let component: FoodGroupAssociationComponent;
  let fixture: ComponentFixture<FoodGroupAssociationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FoodGroupAssociationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FoodGroupAssociationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
