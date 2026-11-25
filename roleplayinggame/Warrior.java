
package roleplayinggame;

public class Warrior extends GameCharacter implements Attacker{
    private int armor;

    public Warrior(String name, int level, int maxHealth, int armor) {
        super(name, level, maxHealth);
        this.armor = Math.max(0, armor);
    }

    public int getArmor() {
        return armor;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 10 + armor * 2;
    }

    @Override
    public int takeDamage(int damage) {
        if (!isAlive() || damage <= 0) {
            return 0;
        }
        int reducedDamage = damage - (armor / 2);
        int effectiveDamage = Math.max(1, reducedDamage);
        return super.takeDamage(effectiveDamage);
    }

    @Override
    public int attack(GameCharacter target) {
        if (!isAlive()) {
            System.out.println(getName() + " is defeated and cannot attack.");
            return 0;
        }
        if (target == null || !target.isAlive()) {
            System.out.println("Target is invalid or already defeated.");
            return 0;
        }
        int rawDamage = calculatePower() / 4;
        return target.takeDamage(rawDamage);
    }

    @Override
    public String getCharacterInfo() {
        return "[Warrior] " + getName()
                + " | Level: " + getLevel()
                + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | Armor: " + armor
                + " | Power: " + calculatePower()
                + " | Alive: " + isAlive();
    }
}
