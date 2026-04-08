# 🎬 Movie Ticket Booking System

Hey there! 👋 Welcome to my Movie Ticket Booking engine. I built this project to understand the core backend mechanics behind ticketing systems like BookMyShow. It handles everything from onboarding theatres to booking those exact corner seats for a Friday night premiere!

## 🚀 What does it do?

At its core, it's a RESTful API built with **Spring Boot** and **Java 21**. Here's a quick rundown of what's inside:

- **Users & Secure Auth:** You can register, login, and get a JWT to securely access the app. 
- **Theatres & Movies:** Admins can onboard new local theatres, add movies, and map out available shows.
- **Shows & Schedules:** Set up specific timings for a movie inside a theatre.
- **Seat Booking Engine:** 
  - Browse available seats.
  - Pick your favorite spots.
  - Generates the final billed amount based on the requested seats.
  - Confirm or cancel your bookings (You can't cancel if the show is starting in less than 2 hours—fair is fair!).

## ⚡ Concurrency: Can it handle the rush?

**Yes, it absolutely can!**

One of the biggest challenges in building a ticketing app is "What if 50 people click 'Book' on the exact same seat at the exact same millisecond?" 

If we weren't careful, the system could easily double-book a seat. To solve this, I've implemented **Pessimistic Locking** combined with strict `@Transactional` boundaries on the database level. 

When you start booking a show, the database actively locks that specific show's row from concurrent modifications. Any other requests trying to book seats for that *same* show will automatically queue up and process sequentially. This guarantees 100% data consistency—even during a massive traffic rush for a blockbuster movie!

## 🛠️ The Tech Stack

I kept it clean and standard:
- **Java 21 & Spring Boot 3.x**
- **Spring Data JPA / Hibernate** for database interactions.
- **Spring Security + JWT** to keep the bad guys out.
- **MySQL** as the main database.
- **Lombok** so I don't have to write thousands of getters and setters. 😅
- **Maven** for dependencies.

## 💻 How to run it locally

If you want to spin it up on your own machine, it's super easy:

1. Make sure you have **Java 21** and **Maven** installed.
2. Have a **MySQL** server running.
3. Update the database URL, username, and password in `src/main/resources/application.properties`.
4. Open your terminal in the root folder and run:
   ```bash
   mvn spring-boot:run
   ```
5. The API will start up, and you'll be good to go!

---

Feel free to fork this, open issues, or shoot me a PR if you want to add some cool features! Happy coding! 🍿
