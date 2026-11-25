
package roleplayinggame;

public class Healer extends GameCharacter implements Supporter {
    private int healAmount;

    public Healer(String name, int level, int maxHealth, int healAmount) {
        super(name, level, maxHealth);
        this.healAmount = Math.max(0, healAmount);
    }

    public int getHealAmount() {
        return healAmount;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 5 + healAmount;
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
        int totalHeal = healAmount + getLevel();
        return target.restoreHealth(totalHeal);
    }

    @Override
    public String getCharacterInfo() {
        return "[Healer] " + getName()
                + " | Level: " + getLevel()
                + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | HealAmount: " + healAmount
                + " | Power: " + calculatePower()
                + " | Alive: " + isAlive();
    }
}
