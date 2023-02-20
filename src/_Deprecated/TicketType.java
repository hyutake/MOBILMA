package _Deprecated;

/**
 @deprecated No Longer in development
 */
public enum TicketType implements PriceInterface{
    Child("Child"),
    SENIOR_CITIZEN("Senior"),
    NORMAL("Normal"),
    HOLIDAY("Holiday"), 
    WEEKEND("Weekend");

    private final String types;

    private TicketType(String types) {
        this.types = types;
    }

    public String toString(){
        return types;
    }
}