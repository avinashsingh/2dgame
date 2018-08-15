## Classic 2D Game

Roam around a field finding enemies, fighting them, and winning experience.

## Getting Started

Its a Java Application. Use

```
mvn clean install
```

to compile the project and create artifact. Run project using

```
java -jar target/codingpuzzle-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

OR

Use

```
mvn exec:java
```

to run using maven

## Design Brief

- Abstract `Display`, `GameCharacter`, `IGameGround`. Custom Implementations can be used in Game class
- GameCharacters use Weapon(s) available from `WeaponFactory`. More weapons can be used by adding to WeaponFactory.
- Override `WeaponSuperiority` to define logic weapon fight.
- Each Stage (New Game, Play Game, Load Game etc) of game has a panel defined capable of accepting user inputs. Atleast one panel will be active at any time. 
- Add Experience change listener to Game class using `Game.getInstance().addExperienceChangeListener(experienceChangeListener)` to receive updates on experience.
