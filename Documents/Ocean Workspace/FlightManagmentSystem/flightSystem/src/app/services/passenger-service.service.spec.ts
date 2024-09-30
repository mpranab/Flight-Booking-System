import { TestBed } from '@angular/core/testing';

import { PassengerServiceService } from './passenger-service.service';

describe('PassengerServiceService', () => {
  let service: PassengerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PassengerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
