import { TestBed } from '@angular/core/testing';
import { KidsReportService } from './kids-report.service';
describe('KidsReportService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(KidsReportService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=kids-report.service.spec.js.map