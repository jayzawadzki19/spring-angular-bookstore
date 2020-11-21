import { Component, OnInit } from '@angular/core';
import {LocalStorageService} from "ngx-webstorage";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private localStorage: LocalStorageService) { }

  ngOnInit(): void {
  }

  getUsername() {
    return this.localStorage.retrieve('username');
  }

}
