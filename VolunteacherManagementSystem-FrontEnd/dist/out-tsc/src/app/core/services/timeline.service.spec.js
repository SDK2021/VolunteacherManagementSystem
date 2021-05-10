import { TestBed } from '@angular/core/testing';
import { TimelineService } from './timeline.service';
describe('TimelineService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(TimelineService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=timeline.service.spec.js.map