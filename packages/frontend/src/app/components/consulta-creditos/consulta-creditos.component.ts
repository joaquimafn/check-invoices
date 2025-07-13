import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, EMPTY, Subject, of } from 'rxjs';
import { catchError, finalize, takeUntil, tap } from 'rxjs/operators';
import { Credito } from 'src/app/services/credito.model';
import { CreditoService } from 'src/app/services/credito.service';

@Component({
  selector: 'app-consulta-creditos',
  templateUrl: './consulta-creditos.component.html',
  styleUrls: ['./consulta-creditos.component.scss']
})
export class ConsultaCreditosComponent implements OnInit, OnDestroy {
  form!: FormGroup;
  loading = false;
  creditos$!: Observable<Credito[]>;

  private destroy$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private creditoService: CreditoService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      numeroNfse: [null, [Validators.required]]
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  buscar(): void {
    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    const numeroNfse = this.form.get('numeroNfse')?.value;

    this.creditos$ = this.creditoService.buscarPorNfse(numeroNfse).pipe(
      takeUntil(this.destroy$),
      tap(data => {
        if (data && data.length === 0) {
          this.snackBar.open('Não foram encontrados créditos para o número da NFS-e informado.', 'Fechar', { duration: 5000 });
        }
      }),
      catchError(error => {
        console.error('Erro ao buscar créditos:', error);
        const errorMessage = error.error?.message || 'Não foram encontrados créditos para o número da NFS-e informado.';
        this.snackBar.open(errorMessage, 'Fechar', { duration: 5000 });
        return of([]);
      }),
      finalize(() => {
        this.loading = false;
      })
    );
  }
}
