import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSessionsComponent } from './create-sessions.component';

describe('CreateSessionsComponent', () => {
  let component: CreateSessionsComponent;
  let fixture: ComponentFixture<CreateSessionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateSessionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSessionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
