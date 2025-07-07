import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { finalize } from 'rxjs/operators';
import { Credito } from 'src/app/services/credito.model';
import { CreditoService } from 'src/app/services/credito.service';

@Component({
  selector: 'app-consulta-por-credito',
  templateUrl: './consulta-por-credito.component.html',
  styleUrls: ['./consulta-por-credito.component.scss']
})
export class ConsultaPorCreditoComponent implements OnInit {
  form!: FormGroup;
  loading = false;
  credito: Credito | null = null;

  constructor(
    private fb: FormBuilder,
    private creditoService: CreditoService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      numeroCredito: [null, [Validators.required]]
    });
  }

  buscar(): void {
    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.credito = null;
    const numeroCredito = this.form.get('numeroCredito')?.value;

    this.creditoService.buscarPorNumeroCredito(numeroCredito).pipe(
      finalize(() => this.loading = false)
    ).subscribe({
      next: (data) => {
        this.credito = data;
      },
      error: (error) => {
        const errorMessage = error.error?.message || 'Crédito não encontrado.';
        this.snackBar.open(errorMessage, 'Fechar', { duration: 5000 });
      }
    });
  }

  novaBusca(): void {
    this.credito = null;
    this.form.reset();
  }
}
