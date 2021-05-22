import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PdfsViewerComponent } from './pdfs-viewer.component';

describe('PdfsViewerComponent', () => {
  let component: PdfsViewerComponent;
  let fixture: ComponentFixture<PdfsViewerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PdfsViewerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PdfsViewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
