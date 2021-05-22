import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KidsTableComponent } from './kids-table.component';

describe('KidsTableComponent', () => {
  let component: KidsTableComponent;
  let fixture: ComponentFixture<KidsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KidsTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KidsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
