package rw.enums;

/**
 * Enumeration of PredaCon weapon types
 *
 * @author Jonathan Hudson
 * @version 1.0
 */
public enum WeaponType {

    //3 types, with increasing strength
    CLAWS(2), LASER(3), TEETH(4);

    /**
     * Each weapon has a strength
     */
    private final int weaponStrength;

    /**
     * Weapons are created wit ha set strength
     *
     * @param weaponStrength The strength of the weapon
     */
    WeaponType(int weaponStrength) {
        this.weaponStrength = weaponStrength;
    }

    /**
     * Helper to look up a matching weapon based on a single character that is first letter of name
     *
     * @param type The char that is the first letter of weapon type (upper case)
     * @return The enum type of the weapon
     */
    public static WeaponType getWeaponType(char type) {
        if (type == 'C') {
            return CLAWS;
        } else if (type == 'L') {
            return LASER;
        } else if (type == 'T') {
            return TEETH;
        } else {
            throw new IllegalArgumentException("Weapon type " + type + " is not valid (C)laws/(L)aser/(T)eeth)!");
        }
    }

    /**
     * Get the strength of the weapon
     *
     * @return The positive integer weapon strength
     */
    public int getWeaponStrength() {
        return weaponStrength;
    }
}