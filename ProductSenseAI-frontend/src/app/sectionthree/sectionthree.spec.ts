import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Sectionthree } from './sectionthree';

describe('Sectionthree', () => {
  let component: Sectionthree;
  let fixture: ComponentFixture<Sectionthree>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Sectionthree]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Sectionthree);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
