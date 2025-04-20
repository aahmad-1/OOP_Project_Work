# Lutemon Battle Game 🏟️


Turn-based Android game where users create, train, and battle colorful creatures called Lutemons. Built with Java in Android Studio following OOP principles.

**Train your team. Fight for glory. Keep your Lutemons alive.**

## Team members

- [Ahmad Paturusi][1]: UI/UX design, Lutemon assets, and button interactions
- [Raja Manan Tassadiq][2]: Animations, statistics tracking, and Figma prototyping
- [Blaise Mwubahamana][3]: Battle logic, data persistence, and file I/O implementation

[1]: https://github.com/aahmad-1
[2]: https://github.com/Manan-codes
[3]: https://github.com/blaise-creator

## Features ✨

### Core Gameplay
- 🧠 **Turn-based Combat**: Lutemons battle using attack/defense stats
- 🏋️ **Training System**: Gain experience to boost attack power
- ⚔️ **Battle Simulation**: Real-time battle logs with dynamic UI updates

### Lutemon Management
- 🏡 **Home Area**: Regenerates health
- 🎯 **Training Area**: Increases experience
- 🏟️ **Battle Arena**: Fight other Lutemons
- 🗃️ **Persistent Storage**: Save/load progress via local files

### Technical Highlights
- 🖼️ **Custom RecyclerViews**: Dynamic lists with stats and images
- 🎨 **Unique Lutemon Visuals**: Color-coded drawables for each type
- 📊 **Stats Tracking**: Name, color, attack, defense, health, XP, movement history

## Lutemon Types 🎨

| Color  | Attack | Defense |   HP   |
|--------|--------|---------|--------|
| White  | 5      | 4       | 20     |
| Green  | 6      | 3       | 19     |
| Pink   | 7      | 2       | 18     |
| Orange | 8      | 1       | 17     |
| Black  | 9      | 0       | 16     |

*All Lutemons start at full health with 0 XP (each XP adds +1 attack)*

## Technical Implementation ⚙️

### OOP Principles
- Encapsulation, Inheritance, Polymorphism
- Abstract classes, static methods

### Data Structures
- `ArrayList<Lutemon>` for collections
- `HashMap<Integer, Lutemon>` for ID-based access

### Android Components & Compatability
- Tested across various Android phones
- File I/O for game state persistence
- RecyclerView for dynamic lists
- Custom adapters for Lutemon displays


## Bonus Features 🎁
✅ Dynamic RecyclerView implementations
✅ Distinct visual identity for each Lutemon
✅ Real-time battle logs in UI
✅ Complete save/load functionality
✅ Stat tracking (XP, battles won, battles lost, battles completed, etc)
✅ Battle visualization with animations
✅ Modular UI with Fragments
