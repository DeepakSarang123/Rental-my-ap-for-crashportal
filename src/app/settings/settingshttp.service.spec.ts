import { TestBed } from '@angular/core/testing';

import { SettingshttpService } from './settingshttp.service';

describe('SettingshttpService', () => {
  let service: SettingshttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SettingshttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
