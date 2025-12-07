package model;

public class PremiumMembership extends Membership{
    @Override
    public int getBorrowlimit(){
        return 5;
    }
    @Override
    public String getTypeName(){
        return "PREMIUM";
    }

}
