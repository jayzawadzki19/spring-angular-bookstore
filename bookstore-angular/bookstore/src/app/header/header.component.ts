import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {SignupComponent} from "../auth/signup/signup.component";
import {LoginComponent} from "../auth/login/login.component";
import {AuthService} from "../auth/shared/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {

  username: string;
  isLoggedIn: boolean;

  constructor(private modalService: NgbModal, private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    this.isLoggedIn = this.authService.isLoggedIn();
    console.log(this.username);
  }

  open() {
    const modalRef = this.modalService.open(SignupComponent);
    modalRef.componentInstance.name = 'Signup';
  }

  login() {
    const modalRef = this.modalService.open(LoginComponent);
    modalRef.componentInstance.name = 'Login';
  }

  logout() {
    this.authService.logout();
    this.isLoggedIn = false;
    this.router.navigateByUrl('');
  }
}
