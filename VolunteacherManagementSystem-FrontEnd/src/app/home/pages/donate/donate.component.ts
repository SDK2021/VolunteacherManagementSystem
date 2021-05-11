import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { finalize } from 'rxjs/operators';
import { Donor } from 'src/app/core/model/donor';
import { Payment } from 'src/app/core/model/payment';
import { Usertype } from 'src/app/core/model/usertype';
import { AppHomeService } from 'src/app/user/services/app-home.service';
import { UsersService } from 'src/app/user/services/users.service';

@Component({
  selector: 'app-donate',
  templateUrl: './donate.component.html',
  styleUrls: ['./donate.component.css']
})
export class DonateComponent implements OnInit {
  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  userTypes:Array<Usertype>
  donor:Donor = new Donor()
  payment:Payment = new Payment()
  userTypeId:number

  constructor(private _formBuilder: FormBuilder,private userService:UsersService,private homeService:AppHomeService) { }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
    this.payment = new Payment()
    this.donor = new Donor()
    this.userTypes = []
    this.getAllUserType();
  }
 getAllUserType() {
    this.userService.getAllUserType().subscribe(data=>{
      this.userTypes = data
    })
  }

  selectedUserType(event)
  {
    this.userTypeId = event.target.value
  }
  addPayment(form)
  {
    let today:Date = new Date()
 //   this.payment.paymentTime = today.getHours().toString() + "-" +today.getMinutes().toString() + "-" + today.getSeconds().toString()
    this.payment.donor = this.donor
    
    this.userService.getUserType( this.userTypeId).pipe(finalize(()=>{
      this.homeService.addPayment(this.payment).subscribe(data=>{
        console.log(this.payment.donor.userType); 
      })
        window.location.href = "http://localhost:9090/vms/redirect-paytm?phonenumber="+this.donor.donorPhone +"&amount="+this.payment.amount +"&email="+this.payment.donor.donorEmail
    })).subscribe(data=>{
      this.payment.donor.userType = data
    })
  }
}
