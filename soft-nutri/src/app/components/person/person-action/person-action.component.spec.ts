import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonActionComponent } from './person-action.component';

describe('PersonActionComponent', () => {
  let component: PersonActionComponent;
  let fixture: ComponentFixture<PersonActionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
    imports: [PersonActionComponent]
})
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
