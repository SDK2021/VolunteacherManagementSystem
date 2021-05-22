import { TestBed } from '@angular/core/testing';

import { KidsService } from './kids.service';

describe('KidsService', () => {
  let service: KidsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KidsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
