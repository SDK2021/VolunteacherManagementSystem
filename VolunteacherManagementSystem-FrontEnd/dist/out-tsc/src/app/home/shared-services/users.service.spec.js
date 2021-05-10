import { TestBed } from '@angular/core/testing';
import { UsersService } from './users.service';
describe('UsersService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(UsersService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=users.service.spec.js.map