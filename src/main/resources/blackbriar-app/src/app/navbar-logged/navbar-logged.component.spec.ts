import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarLoggedComponent } from './navbar-logged.component';

describe('NavbarLoggedComponent', () => {
  let component: NavbarLoggedComponent;
  let fixture: ComponentFixture<NavbarLoggedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavbarLoggedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarLoggedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
