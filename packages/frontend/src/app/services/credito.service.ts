import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credito } from './credito.model';

@Injectable({
  providedIn: 'root'
})
export class CreditoService {
  private apiUrl = 'http://localhost:8080/api/v1/creditos';

  constructor(private http: HttpClient) { }

  buscarPorNfse(numeroNfse: number): Observable<Credito[]> {
    return this.http.get<Credito[]>(`${this.apiUrl}/nfse/${numeroNfse}`);
  }

  buscarPorNumeroCredito(id: number): Observable<Credito> {
    return this.http.get<Credito>(`${this.apiUrl}/${id}`);
  }
}
