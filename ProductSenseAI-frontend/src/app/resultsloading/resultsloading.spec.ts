import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Resultsloading } from './resultsloading';

describe('Resultsloading', () => {
  let component: Resultsloading;
  let fixture: ComponentFixture<Resultsloading>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Resultsloading]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Resultsloading);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
