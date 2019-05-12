import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarNoLoggedComponent } from './navbar-no-logged.component';

describe('NavbarNoLoggedComponent', () => {
  let component: NavbarNoLoggedComponent;
  let fixture: ComponentFixture<NavbarNoLoggedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavbarNoLoggedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarNoLoggedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
