import { TestBed } from '@angular/core/testing';
import { ProjectsService } from './projects.service';
describe('ProjectsService', function () {
    var service;
    beforeEach(function () {
        TestBed.configureTestingModule({});
        service = TestBed.inject(ProjectsService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=projects.service.spec.js.map