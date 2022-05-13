import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardFiveComponent } from './card-five.component';

describe('CardFiveComponent', () => {
  let component: CardFiveComponent;
  let fixture: ComponentFixture<CardFiveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardFiveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardFiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
