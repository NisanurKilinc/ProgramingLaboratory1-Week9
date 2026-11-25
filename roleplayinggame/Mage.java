
package roleplayinggame;

public class Mage extends GameCharacter implements Attacker, Supporter {
    private int mana;

    public Mage(String name, int level, int maxHealth, int mana) {
        super(name, level, maxHealth);
        this.mana = Math.max(0, mana);
    }

    public int getMana() {
        return mana;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 8 + mana;
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
        if (mana < 20) {
            System.out.println(getName() + " does not have enough mana (20 required) to attack!");
            return 0;
        }
        int powerBeforeManaCost = calculatePower();
        mana -= 20;
        int rawDamage = powerBeforeManaCost / 3;
        return target.takeDamage(rawDamage);
    }

    @Override
    public int heal(GameCharacter target) {
        if (!isAlive()) {
            System.out.println(getName() + " is defeated and cannot heal.");
            return 0;
        }
        if (target == null || !target.isAlive()) {
            System.out.println("Target cannot be healed (invalid or defeated).");
            return 0;
        }
        if (mana < 15) {
            System.out.println(getName() + " does not have enough mana (15 required) to heal!");
            return 0;
        }
        mana -= 15;
        int healAmount = getLevel() * 2;
        return target.restoreHealth(healAmount);
    }

    @Override
    public String getCharacterInfo() {
        return "[Mage] " + getName()
                + " | Level: " + getLevel()
                + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | Mana: " + mana
                + " | Power: " + calculatePower()
                + " | Alive: " + isAlive();
    }
}
