import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KidsAttendanceComponent } from './kids-attendance.component';

describe('KidsAttendanceComponent', () => {
  let component: KidsAttendanceComponent;
  let fixture: ComponentFixture<KidsAttendanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KidsAttendanceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KidsAttendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
