import { TestBed, inject } from '@angular/core/testing';

import { JobApplicationService } from './job-application.service';

describe('JobApplicationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [JobApplicationService]
    });
  });

  it('should be created', inject([JobApplicationService], (service: JobApplicationService) => {
    expect(service).toBeTruthy();
  }));
});
