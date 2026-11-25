
package roleplayinggame;

import java.util.ArrayList;

public class Team {
    private ArrayList<GameCharacter> members;

    public Team() {
        this.members = new ArrayList<>();
    }

    public boolean addCharacter(GameCharacter character) {
        if (character == null) {
            System.out.println("Cannot add null character.");
            return false;
        }
        if (findByName(character.getName()) != null) {
            System.out.println("Character with name '" + character.getName() + "' already exists in the team!");
            return false;
        }
        members.add(character);
        return true;
    }

    public boolean removeCharacter(String name) {
        GameCharacter found = findByName(name);
        if (found != null) {
            members.remove(found);
            return true;
        }
        return false;
    }

    public GameCharacter findByName(String name) {
        if (name == null) {
            return null;
        }
        for (GameCharacter c : members) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public int attackAll(GameCharacter target) {
        int totalDamageDealt = 0;
        for (GameCharacter c : members) {
            if (c.isAlive() && c instanceof Attacker) {
                int dealt = ((Attacker) c).attack(target);
                System.out.println("  -> " + c.getName() + " attacked " + target.getName() + " for " + dealt + " damage.");
                totalDamageDealt += dealt;
            }
        }
        return totalDamageDealt;
    }

    public int healTeam() {
        int totalHealed = 0;
        for (GameCharacter c : members) {
            if (c.isAlive() && c instanceof Supporter) {
                GameCharacter lowestRatioTarget = null;
                double lowestRatio = Double.MAX_VALUE;

                for (GameCharacter candidate : members) {
                    if (candidate.isAlive()) {
                        double ratio = (double) candidate.getHealth() / candidate.getMaxHealth();
                        if (ratio < lowestRatio) {
                            lowestRatio = ratio;
                            lowestRatioTarget = candidate;
                        }
                    }
                }

                if (lowestRatioTarget != null) {
                    int healed = ((Supporter) c).heal(lowestRatioTarget);
                    System.out.println("  -> " + c.getName() + " healed " + lowestRatioTarget.getName() + " for " + healed + " HP.");
                    totalHealed += healed;
                }
            }
        }
        return totalHealed;
    }

    public int getTotalPower() {
        int totalPower = 0;
        for (GameCharacter c : members) {
            if (c.isAlive()) {
                totalPower += c.calculatePower();
            }
        }
        return totalPower;
    }

    public void displayTeam() {
        System.out.println("=== Team Roster ===");
        if (members.isEmpty()) {
            System.out.println("(Team is empty)");
            return;
        }
        for (GameCharacter c : members) {
            System.out.println("  " + c.getCharacterInfo());
        }
        System.out.println("Total Alive Power: " + getTotalPower());
    }
}
