import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalDeleteComponent } from './professional-delete.component';

describe('ProfessionalDeleteComponent', () => {
  let component: ProfessionalDeleteComponent;
  let fixture: ComponentFixture<ProfessionalDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessionalDeleteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfessionalDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
