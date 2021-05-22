import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditKidComponent } from './edit-kid.component';

describe('EditKidComponent', () => {
  let component: EditKidComponent;
  let fixture: ComponentFixture<EditKidComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditKidComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditKidComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
