export interface Credito {
  id: number;
  creditNumber: string;
  invoiceNumber: string;
  constitutionDate: string;
  invoicedValue: number;
  taxableAmount: number;
  issqnValue: number;
  creditType: string;
  simplifiedTaxSystem: boolean;
  taxRate: number;
  deductionValue: number;
}
