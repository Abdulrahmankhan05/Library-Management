package model;

public class PremiumMembership extends Membership{
    @Override
    public int getBorrowLimit(){
        return 5;
    }
    @Override
    public String getTypeName(){
        return "PREMIUM";
    }

}
