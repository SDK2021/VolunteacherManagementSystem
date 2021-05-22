import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddKidsComponent } from './add-kids.component';

describe('AddKidsComponent', () => {
  let component: AddKidsComponent;
  let fixture: ComponentFixture<AddKidsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddKidsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddKidsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
