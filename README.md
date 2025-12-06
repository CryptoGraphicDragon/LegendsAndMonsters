# Legends and Monsters Game (Java Console)

Java Console game to play as 1 to 3 heroes who battle their way past monsters. Lightly based on Dungeons and Dragons.


---
## 1. Student Information
| Field          | Value          |
|----------------|----------------|
| Students Name  | *Aine Holland* |
| Students ID    | *U13295613*    |
| Course         | *CS611>*       |
| Assignment     | *Assignment 3* |
| Date Submitted | *11-26-2025*   |

---
## 2. Detailed File Information
Current Java source files grouped by package:

**project.Game**
- `Dice.java`
- `Game.java`
- `GameController.java`
- `Input.java`
- `ReadFile.java`
- `Render.interface`

**project.Board**
- `Board.java` 
- `Position.java`
- `Shop.java`
- `Tiles.java`

**project.Heroes**
- `Hero.java`
- `HeroFactory.java`
- `Spell.java`
- `SpellFactory.java`

**project.Inventory**
- `Armor.java`
- `Inventory.java`
- `Item.java`
- `ItemFactory.java`
- `Potion.java`
- `Weapon.java`

**project.Monsters**
- `Monster.java`
- `MonsterFactory.java`

**project.Files (text files for storing game info)**
- `Armory.txt`
- `ASCII.txt`
- `FireSpells.txt`
- `HealingSpells.txt`
- `Heroes.txt`
- `IceSpells.txt`
- `LightningSpells.txt`
- `Monsters.txt`
- `Names.txt`
- `Weaponry.txt`

**Game (root package)**
- `Main.java` (entry point – Main method)

---
## 3. Features
* **Create Your Own Character**: Create own name and choose your own class.
* **Dynamic Board Sizes**: User can press Enter for default `3 3` or enter custom `rows cols`.
* **Clean Run Script**: `run_no_build.bat` compiles to a temp folder and cleans up afterward (nothing committed).
* **Separation of Concerns**: Core contracts, engine loop, IO/renderer, stats, and each game implementation isolated.

---
## 4. Build & Run
The project is structured with the root package `project` inside `src/main/java/`.
To compile and run from the terminal, navigate to the project's root directory (the one containing `src`).

4.1**Navigate to the Source Directory:**
    ```bash
    You should have something like this:
    cd "<directory>\ASS2"
    ```

4.2.**Create bin Directory:**
    ```bash
    mkdir bin
    ```

4.3.**Compile the Code:**
    ```bash
    javac -d bin src\main\java\project\*.java src\main\java\project\controller\*.java src\main\java\project\core\*.java src\main\java\project\engine\*.java src\main\java\project\io\*.java src\main\java\project\stats\*.java src\main\java\project\games\puzzle\*.java src\main\java\project\games\dotsandboxes\*.java```

4.4**Run the Game:**
    ```bash
    java -cp src\main\java project.Main
    ```
NB: We used ANSI colors for Dots & Boxes. 
If your console does not support them, you might see garbled output.
To disable colors, set the environment variable `$env:NO_COLOR="true"` before running the program.

---
## 5. Known Limitations / Notes
* Size only goes up to 10x10 as only have monsters of level 10
* Renderer clears screen (`ESC[H ESC[2J]`); older shells may not fully clear.
* No GUI; console only.
* No save/load; play sessions are ephemeral.

---
## 6. Attribution
All source authored by the project owner (academic / assignment context). No external runtime dependencies beyond the JDK.

---
## 7. Notes (Design / Usage)

---
## 8. Additional I/O Examples


*End of README.*