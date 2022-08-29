import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalActionComponent } from './professional-action.component';

describe('ProfessionalActionComponent', () => {
  let component: ProfessionalActionComponent;
  let fixture: ComponentFixture<ProfessionalActionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessionalActionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfessionalActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
