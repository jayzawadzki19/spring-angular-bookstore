import {Component, OnInit} from '@angular/core';
import {User} from "../../../shared/model/user/user";
import {UserService} from "../../../core/service/user-service/user.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "../../../core/validators/custom-validators";
import {PasswordDto} from "../../../shared/model/user/passwordDto";
import {throwError} from "rxjs";

@Component({
  selector: 'app-userpage',
  templateUrl: './userpage.component.html',
  styleUrls: ['./userpage.component.css']
})
export class UserpageComponent implements OnInit {

  user: User;
  updateUser: User;
  updateForm: FormGroup;
  isError: boolean;
  passwordUpdated: boolean;
  passwordDto: PasswordDto;

  constructor(private userService: UserService) {
    this.updateUser = {
      userId: 0,
      username: '',
      password: '',
      email: 'email',
      phone: '',
      address: '',
    };

    this.passwordDto = {
      oldPassword: '',
      newPassword: ''
    };
  }

  ngOnInit(): void {
    this.passwordUpdated = false;
    this.userService.getUserInfo().subscribe(user => {
      this.user = user;
    });

    this.updateForm = new FormGroup({
      password: new FormControl('', Validators.compose([
          Validators.required,
          Validators.minLength(5),
          CustomValidators.patternValidator(/\d/, {hasNumber: true}),
          CustomValidators.patternValidator(/[A-Z]/, {hasCapitalCase: true}),
          CustomValidators.patternValidator(/[a-z]/, {hasSmallCase: true}),
        ])
      ),
      oldPassword: new FormControl('', Validators.required),
      // phone: new FormControl('',Validators.required),
      // address: new FormControl('',Validators.required)

    })
  }

  updatePassword() {
    this.passwordDto.oldPassword = this.updateForm.get('oldPassword').value;
    this.passwordDto.newPassword = this.updateForm.get('password').value;

      this.userService.updatePassword(this.passwordDto).subscribe(data =>{
        this.isError = false;
        this.passwordUpdated = true;
      }, error => {
        this.isError = true;
        throwError(error);
      });
  }

  updateInfo() {

  }
}
