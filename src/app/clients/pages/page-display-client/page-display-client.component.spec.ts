import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageDisplayClientComponent } from './page-display-client.component';

describe('PageDisplayClientComponent', () => {
  let component: PageDisplayClientComponent;
  let fixture: ComponentFixture<PageDisplayClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageDisplayClientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageDisplayClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
