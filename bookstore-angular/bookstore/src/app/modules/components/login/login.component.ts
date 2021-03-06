import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../core/authentication/auth.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginRequest} from "../../../shared/model/login/login-request";
import {throwError} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequest: LoginRequest;
  loginForm: FormGroup;
  isError: boolean;

  constructor(private authService: AuthService,
              public activeModal: NgbActiveModal,
              private router: Router) {
    this.loginRequest = {
      username: '',
      password: ''
    };
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.compose([
        Validators.required,
        Validators.minLength(5)]))
    })
  }

  login() {
    this.loginRequest.username = this.loginForm.get('username').value;
    this.loginRequest.password = this.loginForm.get('password').value;

    this.authService.login(this.loginRequest)
      .subscribe(data => {
        this.isError = false;
        this.router.navigateByUrl('');
        this.activeModal.close("Login click")

      }, error => {
        this.isError = true;
        throwError(error);
      });
  }
}
