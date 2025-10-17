import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Sectiontwo } from './sectiontwo';

describe('Sectiontwo', () => {
  let component: Sectiontwo;
  let fixture: ComponentFixture<Sectiontwo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Sectiontwo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Sectiontwo);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
