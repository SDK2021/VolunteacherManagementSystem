import { TestBed } from '@angular/core/testing';
import { EventsService } from './events.service';
describe('EventsService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(EventsService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=events.service.spec.js.map