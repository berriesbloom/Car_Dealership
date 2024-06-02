# Car Dealership (Velocity Motors)

This project is meant to be an exercise for various programming concepts, practices, and tools such as: SOLID, Design Patterns, Database connectivity, Spring, JUnit, CRUD, while also trying to replicate an authentic car dealership website experience.

## Features
### User registration
New users can register, while existing users can log into their accounts using credentials. The users can interact with the website in an interactive way, having the ability to search for cars they like and buy them.

### Car Viewing in detail
Each car can be viewed in detail through pictures and technical specifications, such as: brand, model, mileage, engine spec, year, capacity, power, and price.

### User Subscription
When finding a suitable vehicle, a user has the option to subscribe to that certain car. When subscribed to a car, the user will be notified via email every time the car's price is updated. Of course, when no longer interested, the user has the option to unsubscribe.

### Billing Report
When purchasing a car, the user can download a billing report, showing the specifications and price of the car.

### Technology Stack
## Backend
Java + Spring Boot: Both are known for their robustness and stability, integration capabilities and scalability. Very useful for building a solid backend.

## Database
MySQL: I chose MySQL because it offers a combination of reliability, performance, scalability, and ease of use, making it suitable for my application.

## Architecture
I've built the backend using REST APIs. This means that instead of relying on traditional server-side rendering, I've structured the backend to communicate with clients through RESTful endpoints over HTTP. It offers flexibility, scalability, and separation of concerns, enabling smooth interactions between the frontend and backend components of the application.

### Get All Users
* GET `/user/get`
  - Retrieves a list of all users
### Signup
* POST `/user/signup`
  - Creates a new user
  - Request Body: JSON representation of User's fields with values
### Update User
* PUT `/user/update`
  - Updates an exsting user by their ID
  - Request Body: JSON representation of User's fields with updated values
### Delete User
* DELETE `/user/delete`
  - Deletes an existing user by their ID
  - Request Body: JSON representation of User's ID field
  
### Get All Cars
* GET `/car/get`
  - Retrieves a list of all cars
### Add New Car
* POST `/car/add`
  - Creates a new car
  - Request Body: JSON representation of Car's fields with values
### Update Car
* PUT `/car/update`
  - Updates an exsting car by their ID
  - Request Body: JSON representation of Car's fields with updated values
### Delete Car
* DELETE `/car/delete`
  - Deletes an existing car by their ID
  - Request Body: JSON representation of Car's ID field
### Delete Car
* DELETE `/car/delete`
  - Deletes an existing car by their ID
  - Request Body: JSON representation of Car's ID field
### Update Car's Stats
* PUT `/car/updateStatus`
  - Updates an exsting car's status by their ID
  - Request Body: JSON representation of Car's status field with updated value
### Get Car By Category
* GET `/car/getByCategory`
  - Retrieves all cars from a certain category
### Get Car By Id
* GET `/car/getById`
  - Retrieves a car based on its ID

### Get All Category
* GET `/category/get`
  - Retrieves all categories
### Add New Category
* POST `/category/add`
  - Creates a new category
  - Request Body: JSON representation of Caetgory's fields with values
### Update Category
* PUT `/category/update`
  - Updates an exsting category by their ID
  - Request Body: JSON representation of Category's fields with updated values
 ### Subscribe
 * POST `/subscriber/subscribe`
   - Subscribes certain user to a car by their ID's
   - Request Param: userId and carId
 ### Unsubscribe
 * DELETE `/subscriber/delete`
   - Unsubscribes certain user from a certain a car by their ID's
   - Request Param: userId and carId

## Database Diagram
![dealereshipv1 0](https://github.com/berriesbloom/Car_Dealership/assets/78302227/7f0a214e-d6ed-4fc5-b845-bf763f7910e6)
