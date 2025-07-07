import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConsultaCreditosComponent } from './components/consulta-creditos/consulta-creditos.component';
import { ConsultaPorCreditoComponent } from './components/consulta-por-credito/consulta-por-credito.component';

const routes: Routes = [
  { path: '', redirectTo: 'consulta-nfse', pathMatch: 'full' },
  { path: 'consulta-nfse', component: ConsultaCreditosComponent },
  { path: 'consulta-credito', component: ConsultaPorCreditoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
