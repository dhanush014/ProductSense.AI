import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Sectionone } from './sectionone';

describe('Sectionone', () => {
  let component: Sectionone;
  let fixture: ComponentFixture<Sectionone>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Sectionone]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Sectionone);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
