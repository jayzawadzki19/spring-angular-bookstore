import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './modules/components/header/header.component';
import { NavbarComponent } from './modules/components/navbar/navbar.component';
import { SignupComponent } from './modules/components/signup/signup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './modules/components/login/login.component';
import {NgxWebstorageModule} from "ngx-webstorage";
import { HomeComponent } from './modules/pages/home/home.component';
import { BooksComponent } from './modules/pages/books/books.component';
import {NgxPaginationModule} from "ngx-pagination";
import { FooterComponent } from './modules/components/footer/footer.component';
import {CookieService} from "ngx-cookie-service";
import {HttpInterceptorService} from "./core/interceptors/http/http-interceptor.service";
import { CartComponent } from './modules/pages/cart/cart.component';
import { UserpageComponent } from './modules/pages/userpage/userpage.component';
import { PageNotFoundComponent } from './modules/pages/page-not-found/page-not-found.component';
import { CheckoutComponent } from './modules/pages/checkout/checkout.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavbarComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    BooksComponent,
    FooterComponent,
    CartComponent,
    UserpageComponent,
    PageNotFoundComponent,
    CheckoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    NgxPaginationModule
  ],
  providers: [
    CookieService,
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: HttpInterceptorService,
    //   multi: true
    // }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
