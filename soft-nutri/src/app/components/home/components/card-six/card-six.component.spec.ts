import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardSixComponent } from './card-six.component';

describe('CardSixComponent', () => {
  let component: CardSixComponent;
  let fixture: ComponentFixture<CardSixComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardSixComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardSixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
