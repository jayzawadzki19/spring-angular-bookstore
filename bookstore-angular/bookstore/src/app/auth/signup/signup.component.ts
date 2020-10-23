import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "./custom-validators";
import {SignupRequest} from "./signup-request";
import {AuthService} from "../shared/auth.service";
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupRequest: SignupRequest;
  signupForm: FormGroup;

  constructor(private authService: AuthService,public activeModal: NgbActiveModal) {
    this.signupRequest = {
      email: '',
      username: '',
      password: ''
    };
  }

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.compose([
          Validators.required,
          Validators.minLength(5),
          CustomValidators.patternValidator(/\d/, {hasNumber: true}),
          CustomValidators.patternValidator(/[A-Z]/, {hasCapitalCase: true}),
          CustomValidators.patternValidator(/[a-z]/, {hasSmallCase: true}),
        ])
      ),
      confirmPassword: new FormControl('', [Validators.required,])
    })
  }

  subbit() {
    this.signupRequest.email = this.signupForm.get('email').value;
    this.signupRequest.username = this.signupForm.get('username').value;
    this.signupRequest.password = this.signupForm.get('password').value;

    this.authService.signup(this.signupRequest)
      .subscribe(data =>{
        console.log(data)
      });
  }
}
