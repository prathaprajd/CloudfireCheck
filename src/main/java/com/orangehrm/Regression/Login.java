package com.orangehrm.Regression;


import org.testng.annotations.Test;

public class Login {
	

    @Test(priority = 2, groups = {"functional-group"})
    public void accountHolderContactSavingAccountDept() {
        System.out.println("I am from accountHolderContactSavingAccountDept method and class is SavingsAccountDepartment");
    }
    
    @Test(priority = 3, groups = {"functional-group"})
    public void accountHolderContactSavingAccountDept3() {
        System.out.println("I am from accountHolderContactSavingAccountDept method and class is SavingsAccountDepartment");
    }
    
    @Test(priority = 5)
    public void accountHolderContactSavingAccountDept4() {
        System.out.println("I am from accountHolderContactSavingAccountDept method and class is SavingsAccountDepartment");
    }
}
