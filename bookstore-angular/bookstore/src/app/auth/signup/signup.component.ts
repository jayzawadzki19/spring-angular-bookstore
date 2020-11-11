import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "../Validators/custom-validators";
import {SignupRequest} from "./signup-request";
import {AuthService} from "../shared/auth.service";
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {throwError} from "rxjs";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupRequest: SignupRequest;
  signupForm: FormGroup;
  isError: boolean;

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

  submit() {
    this.signupRequest.email = this.signupForm.get('email').value;
    this.signupRequest.username = this.signupForm.get('username').value;
    this.signupRequest.password = this.signupForm.get('password').value;

    this.authService.signup(this.signupRequest)
      .subscribe(data =>{
        this.isError = false;
        console.log(data)
        this.activeModal.close("Sixgnup click")
      }, error => {
        this.isError = true;
        throwError(error);
      });
  }
}
