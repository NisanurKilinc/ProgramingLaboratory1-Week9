
package roleplayinggame;

public class RolePlayingGame {

    public static void main(String[] args) {
        Team team = new Team();

        Warrior warrior = new Warrior("Arthur", 10, 150, 20);
        Mage mage = new Mage("Merlin", 12, 100, 50);
        Healer healer = new Healer("Elara", 9, 110, 18);

        team.addCharacter(warrior);
        team.addCharacter(mage);
        team.addCharacter(healer);

        System.out.println("\n--- Testing Invalid Inputs & Duplicate Name ---");
        team.addCharacter(new Warrior("Arthur", 5, 100, 10)); // Duplicate name
        Warrior invalidChar = new Warrior("Rook", 150, -20, 10); // Invalid level & maxHealth
        team.addCharacter(invalidChar);

        Warrior golem = new Warrior("Golem", 15, 220, 16);

        System.out.println("\n--- Initial Status ---");
        team.displayTeam();
        System.out.println(golem.getCharacterInfo());

        System.out.println("\n=== Round 1 ===");
        team.attackAll(golem);
        if (golem.isAlive()) {
            int dmgToWarrior = golem.attack(warrior);
            System.out.println("  -> " + golem.getName() + " attacked " + warrior.getName() + " for " + dmgToWarrior + " damage.");
        }
        team.healTeam();

        System.out.println("\n=== Round 2 ===");
        team.attackAll(golem);
        if (golem.isAlive()) {
            int dmgToMage = golem.attack(mage);
            System.out.println("  -> " + golem.getName() + " attacked " + mage.getName() + " for " + dmgToMage + " damage.");
        }
        team.healTeam();

        System.out.println("\n--- Search, Remove & Display ---");
        GameCharacter searched = team.findByName("Merlin");
        System.out.println("Found character: " + (searched != null ? searched.getCharacterInfo() : "Not found"));

        boolean removed = team.removeCharacter("Rook");
        System.out.println("Removed 'Rook': " + removed);

        team.displayTeam();
        System.out.println(golem.getCharacterInfo());
    }
    
}
