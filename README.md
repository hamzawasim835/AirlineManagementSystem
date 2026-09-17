# ✈️ Airline Management System

By [Hamza Darwish](https://github.com/hamzawasim835) and [Tuğalp Kumlu](https://github.com/tugalpk).

## 📋 About the project

A partnered semester project for Prof. Yunus Emre Selçuk's BLM2012 OOP course at YTÜ, built as a Java Swing desktop application. Users can browse flights by destination, select seats, make reservations, and run a concurrent, multithreading-based booking simulation. The Admin screen lets users view, add, and update flights.

The app loads sample planes and flights at startup. Admin flight changes stay in memory. Reservations and tickets are also created in memory; the sample `reservations.txt` and `tickets.txt` are not loaded at startup. Cancelling a reservation rewrites those two files in the current working directory.

## 📁 Folder structure

```text
AirlineManagementSystem/
├── 88.jar                   Runnable application
├── README.md
└── src/
    ├── airline/
    │   ├── flight/          Flights, routes, planes, and seats
    │   ├── gui/             Swing screens; LoginFrame is the entry point
    │   ├── manager/         Booking logic, reports, simulation, and tests
    │   └── reservation/     Passengers, reservations, tickets, and baggage
    ├── flights.txt          Sample flights loaded at startup
    ├── planes.txt           Sample planes loaded at startup
    ├── reservations.txt     Sample records; rewritten on cancellation
    └── tickets.txt          Sample records; rewritten on cancellation
```

## 💻 Tools used

- Java and the Java standard library for the application logic and file handling.
- Swing for the desktop interface.
- JUnit 5 for three test classes containing 12 test methods.
- Git and GitHub for version control and hosting.

## 🚀 Run the application

Install a JDK (Java 8 or newer). The repository includes a runnable JAR, so no build tool is required to try the app. From the project folder, run:

```powershell
cd src
java -jar ../88.jar
```

The `src` directory must be the working directory: the application looks for `planes.txt` and `flights.txt` there using relative paths. Choose **User** for booking and simulation, or **Admin** to view, add, and update flights. The sample flights are dated January 2026; they are random examples, not current schedules.

## 🏗️ Build from source

The application itself uses only the Java standard library. In PowerShell, from the project folder:

```powershell
New-Item -ItemType Directory -Path build -Force | Out-Null
$sources = Get-ChildItem src/airline -Recurse -Filter *.java | Where-Object Name -NotLike '*Test.java' | ForEach-Object FullName
javac -d build $sources
cd src
java -cp ../build airline.gui.LoginFrame
```

The three `*Test.java` files use JUnit 5 and require JUnit dependencies to compile or run. They are excluded from the command above.

#### Notes

Due to an issue with the previous repo, one of us had to create a new repo from scratch, hence why only one of us appears as a contributor.
