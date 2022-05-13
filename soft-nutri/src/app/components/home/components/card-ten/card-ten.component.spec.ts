import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardTenComponent } from './card-ten.component';

describe('CardTenComponent', () => {
  let component: CardTenComponent;
  let fixture: ComponentFixture<CardTenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardTenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardTenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
