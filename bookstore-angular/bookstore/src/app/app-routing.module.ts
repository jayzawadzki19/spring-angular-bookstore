import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./modules/pages/home/home.component";
import {BooksComponent} from "./modules/pages/books/books.component";
import {CartComponent} from "./modules/pages/cart/cart.component";
import {UserpageComponent} from "./modules/pages/userpage/userpage.component";
import {PageNotFoundComponent} from "./modules/pages/page-not-found/page-not-found.component";
import {CheckoutComponent} from "./modules/pages/checkout/checkout.component";


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'books', component: BooksComponent},
  {path: 'cart', component: CartComponent},
  {path: 'user', component: UserpageComponent},
  {path: 'checkout', component: CheckoutComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
