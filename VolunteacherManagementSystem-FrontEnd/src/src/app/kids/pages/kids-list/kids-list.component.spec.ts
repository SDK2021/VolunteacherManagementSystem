import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KidsListComponent } from './kids-list.component';

describe('KidsListComponent', () => {
  let component: KidsListComponent;
  let fixture: ComponentFixture<KidsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KidsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KidsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
