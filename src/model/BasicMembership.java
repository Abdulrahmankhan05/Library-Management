package model;

public class BasicMembership extends Membership{
    @Override
    public int getBorrowLimit(){
        return 2;
    }

    @Override
    public String getTypeName() {
        return "BASIC";
    }
}
