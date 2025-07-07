import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaPorCreditoComponent } from './consulta-por-credito.component';

describe('ConsultaPorCreditoComponent', () => {
  let component: ConsultaPorCreditoComponent;
  let fixture: ComponentFixture<ConsultaPorCreditoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConsultaPorCreditoComponent]
    });
    fixture = TestBed.createComponent(ConsultaPorCreditoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
