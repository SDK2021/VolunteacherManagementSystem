import { TestBed } from '@angular/core/testing';
import { SessionsService } from './sessions.service';
describe('SessionsService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(SessionsService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=sessions.service.spec.js.map