import { TestBed } from '@angular/core/testing';
import { AppHomeService } from './app-home.service';
describe('AppHomeService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(AppHomeService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=app-home.service.spec.js.map