import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Credito } from 'src/app/services/credito.model';

@Component({
  selector: 'app-tabela-resultados',
  templateUrl: './tabela-resultados.component.html',
  styleUrls: ['./tabela-resultados.component.scss']
})
export class TabelaResultadosComponent implements OnInit, OnDestroy {
  @Input() dataSource: Credito[] = [];

  displayedColumns: string[] = ['creditNumber', 'invoiceNumber', 'invoicedValue', 'constitutionDate', 'creditType'];

  private readonly destroy$ = new Subject<void>();

  constructor(private breakpointObserver: BreakpointObserver) { }

  ngOnInit(): void {
    this.breakpointObserver
      .observe([Breakpoints.XSmall, Breakpoints.Small])
      .pipe(takeUntil(this.destroy$))
      .subscribe(result => {
        if (result.matches) {
          this.displayedColumns = ['creditNumber', 'invoicedValue', 'creditType'];
        } else {
          this.displayedColumns = ['creditNumber', 'invoiceNumber', 'invoicedValue', 'constitutionDate', 'creditType'];
        }
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
