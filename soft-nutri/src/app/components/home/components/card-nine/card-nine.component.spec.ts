import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardNineComponent } from './card-nine.component';

describe('CardNineComponent', () => {
  let component: CardNineComponent;
  let fixture: ComponentFixture<CardNineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardNineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardNineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
