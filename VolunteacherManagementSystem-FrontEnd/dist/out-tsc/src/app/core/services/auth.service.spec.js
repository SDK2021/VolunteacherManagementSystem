import { TestBed } from '@angular/core/testing';
import { AuthService } from './auth.service';
describe('AuthService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(AuthService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=auth.service.spec.js.map