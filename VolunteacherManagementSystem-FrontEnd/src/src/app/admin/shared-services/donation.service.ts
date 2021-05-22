import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Payment } from 'src/app/core/model/payment';

@Injectable({
  providedIn: 'root'
})
export class DonationService {

  constructor(private http: HttpClient) { }

  getDonations(page:number): Observable<Payment[]> {
    return this.http.get<Payment[]>(`${"http://localhost:9090/vms/payments?page="}${page}`)
      .pipe(retry(3))
  }

  deletePayment(id:number) {

    return this.http.delete(`${"http://localhost:9090/vms/payments/"}${id}`).pipe(retry(3))

  }
}
