package model;

public class BasicMembership extends Membership{
    @Override
    public int getBorrowlimit(){
        return 2;
    }

    @Override
    public String getTypeName() {
        return "BASIC";
    }
}
