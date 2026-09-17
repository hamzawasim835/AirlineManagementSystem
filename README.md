# Airline Management System

By Hamza Darwish and [Tuğalp Kumlu](https://github.com/tugalpk).

A partnered semester project: a Java Swing application for browsing flights, reserving seats, managing flights, and simulating concurrent bookings.

## Run the application

Install a JDK (Java 8 or newer). The repository includes a runnable JAR, so no build tool is required to try the app. From the project folder, run:

```powershell
cd src
java -jar ../88.jar
```

The `src` directory must be the working directory: the application looks for `planes.txt` and `flights.txt` there using relative paths. Choose **User** for booking and simulation, or **Admin** to view, add, and update flights. The sample flights are dated January 2026; they are historical examples, not current schedules.

## Build from source

The application itself uses only the Java standard library. In PowerShell, from the project folder:

```powershell
New-Item -ItemType Directory -Path build -Force | Out-Null
$sources = Get-ChildItem src/airline -Recurse -Filter *.java | Where-Object Name -NotLike '*Test.java' | ForEach-Object FullName
javac -d build $sources
cd src
java -cp ../build airline.gui.LoginFrame
```

The three `*Test.java` files use JUnit 5 and require JUnit dependencies to compile or run. They are excluded from the command above.

## Project files

- `src/airline/flight`: flights, routes, planes, and seats.
- `src/airline/reservation`: passengers, reservations, tickets, and baggage.
- `src/airline/manager`: flight and reservation logic, reports, simulation, and tests.
- `src/airline/gui`: Swing screens; `LoginFrame` is the entry point.
- `src/*.txt`: sample planes, flights, reservations, and tickets.
- `88.jar`: packaged application.

The app loads the sample plane and flight files at startup. Flight changes in the Admin screen are held in memory. Reservation and ticket records are generated in memory during a session; the sample `reservations.txt` and `tickets.txt` are not loaded at startup. Cancelling a reservation rewrites those two files in the current working directory.
