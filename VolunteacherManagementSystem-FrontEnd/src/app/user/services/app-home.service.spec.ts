import { TestBed } from '@angular/core/testing';

import { AppHomeService } from './app-home.service';

describe('AppHomeService', () => {
  let service: AppHomeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppHomeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
