# Lutemon Battle Game 


Turn-based Android game where users create, train, and battle colorful creatures called Lutemons. Built with Java in Android Studio following OOP principles.

**Train your team. Fight for glory. Keep your Lutemons alive.**

## Team members

- [Ahmad Paturusi][1]: UI/UX design, Lutemon assets, and button interactions
- [Raja Manan Tassadiq][2]: Animations, statistics tracking, and Figma prototyping
- [Blaise Mwubahamana][3]: Battle logic, data persistence, and file I/O implementation

[1]: https://github.com/aahmad-1
[2]: https://github.com/Manan-codes
[3]: https://github.com/blaise-creator

## Features 

### Core Gameplay
-  **Turn-based Combat**: Lutemons battle using attack/defense stats
-  **Training System**: Gain experience to unlock special abilities
-  **Battle Simulation**: Real-time battle logs with dynamic UI updates

### Lutemon Management
-  **Home Area**: Regenerates health
-  **Training Area**: Increases experience
-  **Battle Arena**: Fight other Lutemons

### Technical Highlights
-  **Custom RecyclerViews**: Dynamic lists with stats and images
-  **Unique Lutemon Visuals**: Color-coded drawables for each type
-  **Stats Tracking**: Name, color, attack, defense, health, XP, movement history

## Lutemon Types 🎨

| Color  | Attack | Defense |   HP   |
|--------|--------|---------|--------|
| White  | 5      | 4       | 20     |
| Green  | 6      | 3       | 19     |
| Pink   | 7      | 2       | 18     |
| Orange | 8      | 1       | 17     |
| Black  | 9      | 0       | 16     |

*All Lutemons start at full health with 0 XP

## Technical Implementation ⚙️

### OOP Principles
- Encapsulation, Inheritance, Polymorphism
- Abstract classes, static methods

### Data Structures
- `ArrayList<Lutemon>` for collections

### Android Components & Compatability
- Tested across various Android phones
- File I/O for game state persistence
- RecyclerView for dynamic lists
- Custom adapters for Lutemon displays


## Bonus Features 🎁
- [x] Dynamic RecyclerView implementations
- [x] Distinct visual identity for each Lutemon
- [x] Real-time battle logs in UI
- [x] Stat tracking (XP, battles won, battles lost, battles completed, etc)
- [x] Battle visualization with animations
- [x] Modular UI with Fragments
