import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./modules/pages/home/home.component";
import {BooksComponent} from "./modules/pages/books/books.component";
import {CartComponent} from "./modules/pages/cart/cart.component";
import {UserpageComponent} from "./modules/pages/userpage/userpage.component";


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'books', component: BooksComponent},
  {path: 'cart', component: CartComponent},
  {path: 'user', component: UserpageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
