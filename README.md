# Coffee Vending Machine

A console coffee vending machine written in **Java**.

Mini project for *Fundamentals of Programming 2* (BSc in Computing, TU Dublin, 2025).

## Features

- Loads the coffee menu and prices from `Inventory.csv`
- Text menus to choose a coffee, cup size and extras
- Pay by cash (with change calculation) or by card
- Prints a receipt and appends every sale to `Transactions.csv`

## Running

Requires Java 11 or newer.

```bash
java Coffee_Vending_App.java
```

Or use `start.bat` (Windows) / `start.sh` (Linux/macOS).

To change the menu, edit `Inventory.csv` (`name, price` on each line).

Project documentation is in [`docs/Documentation.docx`](docs/Documentation.docx).
