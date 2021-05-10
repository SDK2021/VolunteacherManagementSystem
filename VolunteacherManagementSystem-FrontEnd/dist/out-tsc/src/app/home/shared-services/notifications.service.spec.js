import { TestBed } from '@angular/core/testing';
import { NotificationsService } from './notifications.service';
describe('NotificationsService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(NotificationsService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=notifications.service.spec.js.map