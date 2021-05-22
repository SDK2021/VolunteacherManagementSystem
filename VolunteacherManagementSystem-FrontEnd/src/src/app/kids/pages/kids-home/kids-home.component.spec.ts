import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KidsHomeComponent } from './kids-home.component';

describe('KidsHomeComponent', () => {
  let component: KidsHomeComponent;
  let fixture: ComponentFixture<KidsHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KidsHomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KidsHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
