import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VolunteacherFormComponent } from './volunteacher-form.component';

describe('VolunteacherFormComponent', () => {
  let component: VolunteacherFormComponent;
  let fixture: ComponentFixture<VolunteacherFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VolunteacherFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VolunteacherFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
