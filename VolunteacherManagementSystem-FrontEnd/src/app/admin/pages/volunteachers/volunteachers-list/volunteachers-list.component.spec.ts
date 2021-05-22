import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VolunteachersListComponent } from './volunteachers-list.component';

describe('VolunteachersListComponent', () => {
  let component: VolunteachersListComponent;
  let fixture: ComponentFixture<VolunteachersListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VolunteachersListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VolunteachersListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
