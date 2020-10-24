import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {SignupComponent} from "../auth/signup/signup.component";
import {LoginComponent} from "../auth/login/login.component";
// @Component({
//   selector: 'signup-popup',
//   templateUrl: '../auth/signup/signup.component.html',
//   styleUrls: ['../auth/signup/signup.component.css']
// })
//
// export class SignupPopup {
//   @Input() name;
//
//   constructor(public activeModal: NgbActiveModal) {
//   }
//
// }

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {

  constructor(private modalService: NgbModal) {
  }

  ngOnInit(): void {
  }

  open() {
    const modalRef = this.modalService.open(SignupComponent);
    modalRef.componentInstance.name = 'Signup';
  }

  login() {
    const modalRef = this.modalService.open(LoginComponent);
    modalRef.componentInstance.name = 'Login';
  }
}
