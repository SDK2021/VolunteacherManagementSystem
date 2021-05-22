import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KidsReportsListComponent } from './kids-reports-list.component';

describe('KidsReportsListComponent', () => {
  let component: KidsReportsListComponent;
  let fixture: ComponentFixture<KidsReportsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KidsReportsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KidsReportsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
