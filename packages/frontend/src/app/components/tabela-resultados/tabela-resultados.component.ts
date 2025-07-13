import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, Input, OnDestroy, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Credito } from 'src/app/services/credito.model';

@Component({
  selector: 'app-tabela-resultados',
  templateUrl: './tabela-resultados.component.html',
  styleUrls: ['./tabela-resultados.component.scss']
})
export class TabelaResultadosComponent implements OnInit, AfterViewInit, OnDestroy {
  @Input() set dataSource(data: Credito[]) {
    this.tableDataSource = new MatTableDataSource<Credito>(data);
    if (this.paginator) {
      this.tableDataSource.paginator = this.paginator;
    }
  }

  tableDataSource = new MatTableDataSource<Credito>([]);
  displayedColumns: string[] = ['creditNumber', 'invoiceNumber', 'invoicedValue', 'constitutionDate', 'creditType'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  // Textos para a paginação
  paginatorLabels = {
    itemsPerPageLabel: 'Itens por página:',
    nextPageLabel: 'Próxima página',
    previousPageLabel: 'Página anterior',
    firstPageLabel: 'Primeira página',
    lastPageLabel: 'Última página',
    getRangeLabel: (page: number, pageSize: number, length: number) => {
      if (length === 0 || pageSize === 0) {
        return `0 de ${length}`;
      }
      length = Math.max(length, 0);
      const startIndex = page * pageSize;
      const endIndex = startIndex < length ? Math.min(startIndex + pageSize, length) : startIndex + pageSize;
      return `${startIndex + 1} - ${endIndex} de ${length}`;
    }
  };

  private readonly destroy$ = new Subject<void>();
  isMobile = false;

  constructor(private breakpointObserver: BreakpointObserver) { }

  ngOnInit(): void {
    this.setupResponsiveColumns();
  }

  ngAfterViewInit(): void {
    if (this.tableDataSource && this.paginator) {
      this.tableDataSource.paginator = this.paginator;

      // Aplicar as traduções
      this.paginator._intl.itemsPerPageLabel = this.paginatorLabels.itemsPerPageLabel;
      this.paginator._intl.nextPageLabel = this.paginatorLabels.nextPageLabel;
      this.paginator._intl.previousPageLabel = this.paginatorLabels.previousPageLabel;
      this.paginator._intl.firstPageLabel = this.paginatorLabels.firstPageLabel;
      this.paginator._intl.lastPageLabel = this.paginatorLabels.lastPageLabel;
      this.paginator._intl.getRangeLabel = this.paginatorLabels.getRangeLabel;
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private setupResponsiveColumns(): void {
    this.breakpointObserver
      .observe([
        Breakpoints.XSmall,
        Breakpoints.Small,
        Breakpoints.Medium,
        Breakpoints.Large,
        Breakpoints.XLarge
      ])
      .pipe(takeUntil(this.destroy$))
      .subscribe(result => {
        this.isMobile = result.breakpoints[Breakpoints.XSmall] || result.breakpoints[Breakpoints.Small];

        if (this.isMobile) {
          // Para dispositivos móveis, mostrar menos colunas
          this.displayedColumns = ['creditNumber', 'invoicedValue', 'creditType'];
        } else if (result.breakpoints[Breakpoints.Medium]) {
          // Para tablets, mostrar mais colunas, mas não todas
          this.displayedColumns = ['creditNumber', 'invoiceNumber', 'invoicedValue', 'creditType'];
        } else {
          // Para desktop, mostrar todas as colunas
          this.displayedColumns = ['creditNumber', 'invoiceNumber', 'invoicedValue', 'constitutionDate', 'creditType'];
        }
      });
  }
}
