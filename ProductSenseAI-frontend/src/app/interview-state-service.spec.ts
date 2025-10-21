import { TestBed } from '@angular/core/testing';

import { InterviewStateService } from './interview-state-service';

describe('InterviewStateService', () => {
  let service: InterviewStateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InterviewStateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
