import { TestBed } from '@angular/core/testing';

import { UserCanActivateGuard } from './user-can-activate.guard';

describe('UserCanActivateGuard', () => {
  let guard: UserCanActivateGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(UserCanActivateGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
