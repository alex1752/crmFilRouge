import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageSignOutComponent } from './page-sign-out.component';

describe('PageSignOutComponent', () => {
  let component: PageSignOutComponent;
  let fixture: ComponentFixture<PageSignOutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageSignOutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageSignOutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
