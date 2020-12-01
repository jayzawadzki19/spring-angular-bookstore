# spring-angular-bookstore
A full-stack Bookstore web application created with Spring and Angular.
Frontend client makes API calls to the running backend server.

![Site image](./img/site.png)

### Technologies

Project is created with:
* Java 11
* Spring boot 2.3.2
* Spring Data
* Angular 9
* Angular CLI
* Bootstrap
* Maven
* H2Database
* REST
* Lombok

## Features

  - Store allows user to create his account and make orders.
  - User can change his informations inside user panel.
  - Books can be added to cart, which counts full price for every amount of books.
  - Spring Security provides password encoding, authorization and authentication.
  - H2 Database stores data about users, books, cart items, orders.

## Setup

### Backend

To run server go to app root folder and use mvn script:

```sh
$ ./mvn install
```

```sh
$ ./mvn spring-boot:run
```

Backend server is running on [localhost:8080]

### Frontend

To run frontend client go to app root folder and:
Run `npm install`
Then run `ng serve`

Frontend client is running on [localhost:4200]

