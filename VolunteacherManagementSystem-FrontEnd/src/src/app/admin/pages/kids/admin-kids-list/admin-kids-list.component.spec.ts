import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminKidsListComponent } from './admin-kids-list.component';

describe('AdminKidsListComponent', () => {
  let component: AdminKidsListComponent;
  let fixture: ComponentFixture<AdminKidsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminKidsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminKidsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
