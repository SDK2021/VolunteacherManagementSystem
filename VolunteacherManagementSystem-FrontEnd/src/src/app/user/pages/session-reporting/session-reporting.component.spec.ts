import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SessionReportingComponent } from './session-reporting.component';

describe('SessionReportingComponent', () => {
  let component: SessionReportingComponent;
  let fixture: ComponentFixture<SessionReportingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SessionReportingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SessionReportingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
