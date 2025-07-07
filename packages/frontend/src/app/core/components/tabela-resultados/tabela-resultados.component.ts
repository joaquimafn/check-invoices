import { Component } from '@angular/core';

export interface Credit {
  numeroNFS: number;
  dataEmissao: Date;
  valor: number;
  status: string;
}

const ELEMENT_DATA: Credit[] = [
  { numeroNFS: 1, dataEmissao: new Date(), valor: 100.50, status: 'Emitido' },
  { numeroNFS: 2, dataEmissao: new Date(), valor: 250.00, status: 'Aguardando' },
  { numeroNFS: 3, dataEmissao: new Date(), valor: 50.25, status: 'Cancelado' },
];

@Component({
  selector: 'app-tabela-resultados',
  templateUrl: './tabela-resultados.component.html',
  styleUrls: ['./tabela-resultados.component.scss']
})
export class TabelaResultadosComponent {
  displayedColumns: string[] = ['numeroNFS', 'dataEmissao', 'valor', 'status'];
  dataSource = ELEMENT_DATA;
}
