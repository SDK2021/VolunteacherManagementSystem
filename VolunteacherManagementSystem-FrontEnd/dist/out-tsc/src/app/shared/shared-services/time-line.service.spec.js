import { TestBed } from '@angular/core/testing';
import { TimeLineService } from './time-line.service';
describe('TimeLineService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(TimeLineService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=time-line.service.spec.js.map