import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardSevenComponent } from './card-seven.component';

describe('CardSevenComponent', () => {
  let component: CardSevenComponent;
  let fixture: ComponentFixture<CardSevenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardSevenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardSevenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
