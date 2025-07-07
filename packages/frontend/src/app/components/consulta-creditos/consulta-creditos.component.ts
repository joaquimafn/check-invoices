import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, EMPTY } from 'rxjs';
import { catchError, finalize } from 'rxjs/operators';
import { Credito } from 'src/app/services/credito.model';
import { CreditoService } from 'src/app/services/credito.service';

@Component({
  selector: 'app-consulta-creditos',
  templateUrl: './consulta-creditos.component.html',
  styleUrls: ['./consulta-creditos.component.scss']
})
export class ConsultaCreditosComponent implements OnInit {
  form!: FormGroup;
  loading = false;
  creditos$!: Observable<Credito[]>;

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

  buscar(): void {
    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    const numeroNfse = this.form.get('numeroNfse')?.value;

    this.creditos$ = this.creditoService.buscarPorNfse(numeroNfse).pipe(
      finalize(() => this.loading = false),
      catchError(error => {
        const errorMessage = error.error?.message || 'Não foram encontrados créditos para o número da NFS-e informado.';
        this.snackBar.open(errorMessage, 'Fechar', { duration: 5000 });
        return EMPTY;
      })
    );
  }
}
