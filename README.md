# Parking Garage Simulator

A console-based parking garage simulator written in Java 6. It models a three-level garage and supports cars, motorcycles, minibuses, and electric cars.

Each vehicle type calculates its own parking fee through `Jarmu.dij(long percek)`. `Parkolohaz` calls that method polymorphically; it does not use `instanceof` or a `switch` to calculate fees.

## Features

- Three-level parking garage with configurable spaces per level
- Vehicle entry and exit by license plate
- Fee calculation for each vehicle type
- Occupancy display for each level
- Revenue total
- `Ora` interface so tests can control the clock
- PlantUML class diagram source in `UML.puml`

## Requirements

- Java 6
- JUnit 4 to run the tests
- PlantUML to render the diagram as a PNG (optional)

> **Java version note:** JUnit 5 requires Java 8 or later, so this Java 6 project uses JUnit 4. The Stream API stretch goal also requires a newer Java version.

## Project structure

```text
.
├── src/
│   ├── App.java
│   ├── Auto.java
│   ├── ElektromosAuto.java
│   ├── Jarmu.java
│   ├── Kisbusz.java
│   ├── Motor.java
│   ├── Ora.java
│   ├── Parkolohaz.java
│   ├── ParkoloJegy.java
│   └── RendszerOra.java
├── test/
│   ├── JarmuTest.java
│   └── TesztOra.java
├── lib/
├── UML.puml
└── README.md
```

## Fee rules

A partial paid hour is charged as a full hour. For example, a car parked for 31 minutes has one paid minute after its free period, so it is charged for one hour.

| Vehicle | Free time | Fee per started hour after free time | Daily cap |
| --- | ---: | ---: | ---: |
| Car | 30 minutes | 5 lei | 50 lei |
| Motorcycle | 30 minutes | 3 lei | 30 lei |
| Minibus | 30 minutes | 8 lei | 80 lei |
| Electric car | 30 minutes | 9 lei, including charging | 70 lei |

## Compile and run

Run these commands from the project root with a Java 6 JDK:

```bash
mkdir -p bin
javac -source 1.6 -target 1.6 -d bin src/*.java
java -cp bin App
```

The sample program creates a garage with three levels and ten spaces on each level.

## Run the tests

Place the JUnit 4 and Hamcrest JAR files in the `lib` folder. Then compile and run the tests:

```bash
javac -source 1.6 -target 1.6 -cp "bin:lib/*" -d bin test/*.java
java -cp "bin:lib/*" org.junit.runner.JUnitCore JarmuTest
```

On Windows, replace the classpath separator `:` with `;`.

The tests cover:

- A parking duration of 0 minutes
- A parking duration of exactly 30 minutes
- A parking duration of 31 minutes
- The car's daily fee cap
- Entry when the garage is full

## Example console session

```text
Marosvasarhelyi parkolohaz
1 - Behajtas
2 - Kihajtas
3 - Foglaltsag szintenkent
4 - Napi bevetel
0 - Kilepes
Valasztas: 1
Jarmutipus:
1 - Auto
2 - Motor
3 - Kisbusz
4 - Elektromos auto
Valasztas: 1
Rendszam: MS01ABC
Sikeres behajtas. Szint: 1

1 - Behajtas
2 - Kihajtas
3 - Foglaltsag szintenkent
4 - Napi bevetel
0 - Kilepes
Valasztas: 3
1. szint: 1 foglalt, 9 szabad
2. szint: 0 foglalt, 10 szabad
3. szint: 0 foglalt, 10 szabad
```

## UML diagram

The class diagram source is in `UML.puml`. To render it as a PNG using PlantUML:

```bash
plantuml UML.puml
```

## Adding a new vehicle type

To add another vehicle, such as a camper van:

1. Create a class that extends `Jarmu`.
2. Add a constructor that passes the license plate to `super(rendszam)`.
3. Implement `dij(long percek)` with the new vehicle's fee rules.

The parking-house fee logic can then use the new class through the existing `Jarmu` interface.