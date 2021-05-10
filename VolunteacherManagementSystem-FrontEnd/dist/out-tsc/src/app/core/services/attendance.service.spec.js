import { TestBed } from '@angular/core/testing';
import { AttendanceService } from './attendance.service';
describe('AttendanceService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(AttendanceService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=attendance.service.spec.js.map