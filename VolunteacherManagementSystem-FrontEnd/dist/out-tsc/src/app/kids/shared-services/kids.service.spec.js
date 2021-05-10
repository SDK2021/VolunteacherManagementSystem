import { TestBed } from '@angular/core/testing';
import { KidsService } from './kids.service';
describe('KidsService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(KidsService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=kids.service.spec.js.map