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
* **Dynamic Board Sizes**: User can enter custom `rows cols`.
* **Clean Run Script**: `run_no_build.bat` compiles to a temp folder and cleans up afterward (nothing committed).
* **Separation of Concerns**: Core contracts, engine loop, IO/renderer, stats, and each game implementation isolated.
* **Difficulty**: Monsters get stronger and shops get better as you move up the board. At different difficulties you start with higher level monsters and shops.
---
## 4. Build & Run
The project is structured with the root package `project` inside `src/main/java/`.
To compile and run from the terminal, navigate to the project's root directory (the one containing `src`).

4.1**Navigate to the Source Directory:**
    ```bash
    You should have something like this:
    cd "<directory>\Game"
    ```

4.2.**Create bin Directory:**
    ```bash
    mkdir bin
    ```

4.3.**Compile the Code:**
    ```bash
    javac -d . src/Board/*.java src/Game/*.java src/Heroes/*.java src/Inventory/*.java src/Monsters/*.java src/*.java
    ```

4.4**Run the Game:**
    ```bash
    java Main
    ```

### Method 2: Using an IDE

1. Open your Java IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)
2. Import the `Game` folder as a project
3. Set the source folder to `src`
4. Run the `Main.java` file 

---
## 5. Known Limitations / Notes
* Size only goes up to 10x10 as there are only monsters of up to level 10
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
````
Welcome to the game!
What game difficulty would you like? 1. Easy 2. Medium 3. Hard
1
What is the height of your board? 3-10
3
What is the width of your board? 3-10
3
How many players would you like? 1-3
1
Would you like to create your own character (otherwise will generate a random hero) (yes/no): n
Your board:
+------+------+------+
| Shop |      | Shop |
+------+------+------+
| XXX  |      |      |
+------+------+------+
| You  |      |      |
+------+------+------+
Would you like to 1.move 2.manage your character? or 3.quit
2
Name: Amaryllis_Astra    Level: 0    Class: HEALER    Race: HUMAN
Experience: 0
Health: 150    Mana: 150
Dexterity: 3    Strength: 3    Intelligence: 3
Equipped Weapon: Rusted_Sword    Damage die: D6
Spells:
Spark  Level: 0  Type: FIRE  Damage: 10  Mana Cost: 20  DC: 10
Minor_Healing  Level: 0  Type: HEALING  Damage: 20  Mana Cost: 25  DC: 0
Inventory:
Gold: 100
Potions:
Weapons:
Rusted_Sword  Level: 0  Cost: 10  Damage Die: D6
Armor:
Would you like to 1.change weapon 2.change armor 3.use a potion 4.use a spell or 5.go back to the board?
5
+------+------+------+
| Shop |      | Shop |
+------+------+------+
| XXX  |      |      |
+------+------+------+
| You  |      |      |
+------+------+------+
Would you like to 1.move 2.manage your character? or 3.quit
1
Where would you like to move?
[right, quit]
right

FIGHT!
You are fighting:
Name: Big_Bad-Wolf    Level: 1    Health: 35
VS
Name: Amaryllis_Astra    Level: 0    Class: HEALER    Race: HUMAN
Health: 150    Mana: 150
Would you like this hero to 1.attack with weapon or 2.use spell?
1
Swung your Rusted_Sword and did 5 damage
Big_Bad-Wolf attacked Amaryllis_Astra for 10 points of damage
You are fighting:
Name: Big_Bad-Wolf    Level: 1    Health: 30
VS
Name: Amaryllis_Astra    Level: 0    Class: HEALER    Race: HUMAN
Health: 140    Mana: 150
Would you like this hero to 1.attack with weapon or 2.use spell?
1
Swung your Rusted_Sword and did 4 damage
Big_Bad-Wolf attacked Amaryllis_Astra for 10 points of damage
You are fighting:
Name: Big_Bad-Wolf    Level: 1    Health: 26
VS
Name: Amaryllis_Astra    Level: 0    Class: HEALER    Race: HUMAN
Health: 130    Mana: 150
Would you like this hero to 1.attack with weapon or 2.use spell?
1
Swung your Rusted_Sword and did 8 damage
Big_Bad-Wolf attacked Amaryllis_Astra for 10 points of damage
You are fighting:
Name: Big_Bad-Wolf    Level: 1    Health: 18
VS
Name: Amaryllis_Astra    Level: 0    Class: HEALER    Race: HUMAN
Health: 120    Mana: 150
Would you like this hero to 1.attack with weapon or 2.use spell?
1
Swung your Rusted_Sword and did 5 damage
Big_Bad-Wolf attacked Amaryllis_Astra and missed
You are fighting:
Name: Big_Bad-Wolf    Level: 1    Health: 13
VS
Name: Amaryllis_Astra    Level: 0    Class: HEALER    Race: HUMAN
Health: 120    Mana: 150
Would you like this hero to 1.attack with weapon or 2.use spell?
1
Attack missed
Big_Bad-Wolf attacked Amaryllis_Astra and missed
You are fighting:
Name: Big_Bad-Wolf    Level: 1    Health: 13
VS
Name: Amaryllis_Astra    Level: 0    Class: HEALER    Race: HUMAN
Health: 120    Mana: 150
Would you like this hero to 1.attack with weapon or 2.use spell?
1
Swung your Rusted_Sword and did 6 damage
Big_Bad-Wolf attacked Amaryllis_Astra for 10 points of damage
You are fighting:
Name: Big_Bad-Wolf    Level: 1    Health: 7
VS
Name: Amaryllis_Astra    Level: 0    Class: HEALER    Race: HUMAN
Health: 110    Mana: 150
Would you like this hero to 1.attack with weapon or 2.use spell?
1
Swung your Rusted_Sword and did 8 damage
Big_Bad-Wolf has died! You win!
Each hero gets 80 experience and 100 gold pieces
+------+------+------+
| Shop |      | Shop |
+------+------+------+
| XXX  |      |      |
+------+------+------+
|      | You  |      |
+------+------+------+
Would you like to 1.move 2.manage your character? or 3.quit
````
*End of README.*