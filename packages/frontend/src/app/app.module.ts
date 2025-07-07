import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ConsultaCreditosComponent } from './components/consulta-creditos/consulta-creditos.component';
import { ConsultaPorCreditoComponent } from './components/consulta-por-credito/consulta-por-credito.component';
import { TabelaResultadosComponent } from './components/tabela-resultados/tabela-resultados.component';
import { SharedModule } from './shared/shared.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    ConsultaCreditosComponent,
    ConsultaPorCreditoComponent,
    TabelaResultadosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    SharedModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
