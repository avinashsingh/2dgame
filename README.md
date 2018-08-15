## Classic 2D Game

Roam around a field finding enemies, fighting them, and winning experience.

## Getting Started

Its a Java Application. Use

```
mvn clean install
```

to compile the project and create artifact. Run project using

```
java -jar target/codingpuzzle-0.0.1-SNAPSHOT.jar
```

OR

Use

```
mvn exec:java
```

to run using maven

## Design Brief

- Abstract Display, GameCharacters, Game Ground/Display area
- GameCharacters use Weapon(s) available from WeaponFactory
- Each Stage of game has a panel defined capable of accepting user inputs. Atleast one panel will be active at any time. 
