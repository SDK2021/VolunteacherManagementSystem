import { TestBed } from '@angular/core/testing';

import { RoleCanActivateGuard } from './role-can-activate.guard';

describe('RoleCanActivateGuard', () => {
  let guard: RoleCanActivateGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(RoleCanActivateGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
