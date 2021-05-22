import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VillagesComponent } from './villages.component';

describe('VillagesComponent', () => {
  let component: VillagesComponent;
  let fixture: ComponentFixture<VillagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VillagesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VillagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
