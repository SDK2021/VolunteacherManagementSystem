import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KidReportComponent } from './kid-report.component';

describe('KidReportComponent', () => {
  let component: KidReportComponent;
  let fixture: ComponentFixture<KidReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KidReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KidReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
