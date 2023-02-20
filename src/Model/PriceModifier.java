package Model;

/** 
 * Stores the pricing information provided during the booking process
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class PriceModifier {
    /**
     * Identifier for the aspect of pricing that is represented
     */
    private String source;

    /**
     * Base price
     */
    private double base;

    /**
     * Modifier value
     */
    private double modifier;

    public PriceModifier() {
        this.source = "NULL";
        this.base = 0;
        this.modifier = 0;
    }

    /**
     * Creates the PriceModifier object specifically for the base pricing
     * @param base
     */
    public PriceModifier(double base) {
        this.source = "Base";
        this.base = base;
        this.modifier = 1;
    }

    /**
     * Creates the PriceModifier object for the other factors of ticket pricing
     * @param source        Identifier for the factor that is represented
     * @param base          Base pricing
     * @param modifier      Modifier value
     */
    public PriceModifier(String source, double base, double modifier) {
        this.source = source;
        this.base = base;
        this.modifier = modifier;
    }

    /**
     * Get the identifier for the represented aspect of pricing
     * @return String       Factor of pricing
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the identifier for the represented aspect of pricing
     * @param source    New factor of pricing
     */
    public void setSource(String source) {
        this.source = source;
    }
    
    /**
     * Get the base price used
     * @return double   Base price
     */
    public double getBase() {
        return base;
    }

    /**
     * Set the base price to be used
     * @param base      New base price
     */
    public void setBase(double base) {
        this.base = base;
    }

    /**
     * Get the modifier value used
     * @return double   Modifier value
     */
    public double getModifier() {
        return modifier;
    }

    /**
     * Set the modifier value to be used
     * @param modifier  New modifier value
     */
    public void setModifier(double modifier) {
        this.modifier = modifier;
    }

    /**
     * Calculates the additional price from this particular factor of ticket pricing
     * @return double   Additional price incurred
     */
    public double getAdditionalPrice() {
        return base * modifier;
    }

    /**
     * Prints the individual factor pricing details, overriden so that it is formatted for readability
     * @return String       Individual factor pricing information
     */
    @Override
    public String toString() {
        return source + "(mod = " + modifier + "): " + getAdditionalPrice();
    }
    
}
