import { TestBed } from '@angular/core/testing';

import { AdminActivateChildGuard } from './admin-activate-child.guard';

describe('AdminActivateChildGuard', () => {
  let guard: AdminActivateChildGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AdminActivateChildGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
