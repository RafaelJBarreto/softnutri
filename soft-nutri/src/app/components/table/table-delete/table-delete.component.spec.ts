import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableDeleteComponent } from './table-delete.component';

describe('TableDeleteComponent', () => {
  let component: TableDeleteComponent;
  let fixture: ComponentFixture<TableDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableDeleteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
