import { TestBed } from '@angular/core/testing';

import { KidsReportService } from './kids-report.service';

describe('KidsReportService', () => {
  let service: KidsReportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KidsReportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
