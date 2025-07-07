import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaResultadosComponent } from './tabela-resultados.component';

describe('TabelaResultadosComponent', () => {
  let component: TabelaResultadosComponent;
  let fixture: ComponentFixture<TabelaResultadosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TabelaResultadosComponent]
    });
    fixture = TestBed.createComponent(TabelaResultadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
