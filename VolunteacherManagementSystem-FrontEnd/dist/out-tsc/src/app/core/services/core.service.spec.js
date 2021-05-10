import { TestBed } from '@angular/core/testing';
import { CoreService } from './core.service';
describe('CoreService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(CoreService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=core.service.spec.js.map