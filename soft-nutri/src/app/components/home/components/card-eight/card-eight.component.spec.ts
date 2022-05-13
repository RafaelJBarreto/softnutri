import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardEightComponent } from './card-eight.component';

describe('CardEightComponent', () => {
  let component: CardEightComponent;
  let fixture: ComponentFixture<CardEightComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardEightComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardEightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
