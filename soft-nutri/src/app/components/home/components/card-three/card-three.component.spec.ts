import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardThreeComponent } from './card-three.component';

describe('CardThreeComponent', () => {
  let component: CardThreeComponent;
  let fixture: ComponentFixture<CardThreeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardThreeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardThreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
