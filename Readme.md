# RPG Shop Inventory System (Java + MySQL)

A console-based inventory management system inspired by RPG shop mechanics.
Built to practice **Java OOP**, **DAO/Service architecture**, and **MySQL database integration**.

---

## Features

### Base Targets

* **Main Menu** with two perspectives:

  * **Player**: view items, buy/sell, manage inventory.
  * **Shopkeeper**: add stock, adjust prices, discard items, view shop stock.
* **DAO Layer** for database access (JDBC).
* **Service Layer** for game/business logic.
* **UI Layer** with simple console menus.
* **MySQL Integration** for persistent data.

### Stretch Goals (if time allows)

* Transaction logging (history of buys/sells).
* Multiple shops with individual stock and prices.
* Reports: top-selling items, revenue tracking.
* Graphical interface (JavaFX).

---

## Project Structure

```
src/main/java/com/rpgshop
├── dao/          # Database Access Objects (DAO, DAOImpl)
├── model/        # Data models (POJOs like Item, Player, Inventory)
├── service/      # Business logic (ShopService, PlayerService, etc.)
├── ui/           # Menus (MainMenu, PlayerMenu, ShopkeeperMenu)
└── util/         # Helpers (DBConnection, ConsoleUtils)
```

---

## Database Schema

Core tables:

* **items** → global catalog of items (name, category, base price).
* **players** → player profiles with gold balance.
* **player_inventory** → items owned by players.
* **shop_stock** → items stocked by the shop, with quantity (and price if per-shop pricing).
* **transactions** → log of all buy/sell actions.

Schema is versioned via `schema_seed.sql`.

---

## Requirements

* **Java 17+ or Java JDK Full with JavaFX**
* **MySQL 8+ or MySql 8.4**
* **Appache Mavel Installed and Added to Path**
* **IntelliJ IDEA** (recommended)



---

## Setup

1. **Clone the repo**

   ```bash
   git clone https://github.com/MarsPH/JavaInventorySystem.git
  
   ```

2. **Create the database**

   ```bash
   mysql -u root -p < schema_seed.sql

   or manually copy paste the schema_seed.sql and paste in MySql to create the database.
   ```

3. **Configure DB credentials**
   Update `DBConnection.java` with your MySQL username and password:
   or
   Configure your MYSQL creditionls and port to as follow
   port: 3306
   passward: 1234

   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/rpg_shop";
   private static final String USER = "root";
   private static final String PASSWORD = "1234";
   ```

5. **Run the program**

   * Open in IntelliJ.
   * build the project using "mvn clean install"
   * Run `Main.java`.

---

## Usage

* Launch program → Main Menu.
* Choose **Player Menu** to view items or  buy/sell.
* Choose **Shopkeeper Menu** to manage stock.
* Data persists in MySQL database.

---

## Learning Goals

* Practice **Java OOP** and **package structure**.
* Use **DAO + Service layers** for clean architecture.
* Connect Java to MySQL using **JDBC**.
* Demonstrate teamwork on a 2-person project.

---

## Authors

* MarsPh
* AegonSnow

IF YOU ENCOUNTER ANY BUGS OR ANY ISSUES IN SETUP , PLEASE RAISE A ISSUE 
FOR MY COLLEGE COLLEGUES AND TEACHER , PLEASE LEAVE US A MIO.
