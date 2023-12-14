package com.model;

public class Login_account {
    private Staff userAccount;
    private static Login_account instance = null;

    public void setUserAccount(int staffId, String name, int age, String email, String address, String username, String password, int previlege) {
        this.userAccount = new Staff(staffId, name, age, email, address, username, password, previlege);
        instance = this;
    }
    
    public void setUserAccount(Staff staff){
        this.userAccount = staff;
    }
    
    public void freeUserAccount(){
        this.userAccount = null;
    }

    public int getPrevilege() {
        return userAccount.getPrevilege();
    }

    public Staff getUserAccount() {
        return userAccount;
    }

    public Login_account() {
        this.userAccount = new Staff();
    }
    
    
    public static Login_account getInstance() {
        if (instance == null) {
            instance = new Login_account();
        }
        return instance;
    }
     
    
    
    
    
}
