import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetKidsPhotoComponent } from './set-kids-photo.component';

describe('SetKidsPhotoComponent', () => {
  let component: SetKidsPhotoComponent;
  let fixture: ComponentFixture<SetKidsPhotoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SetKidsPhotoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SetKidsPhotoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
