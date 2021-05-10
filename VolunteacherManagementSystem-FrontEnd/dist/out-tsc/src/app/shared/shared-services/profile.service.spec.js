import { TestBed } from '@angular/core/testing';
import { ProfileService } from './profile.service';
describe('ProfileService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(ProfileService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=profile.service.spec.js.map