package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        // not valid emails
        assertFalse( BankAccount.isEmailValid("ab.com"));   // missing '@' symbol
        assertFalse( BankAccount.isEmailValid("@ab.com"));  // missing local part
        assertFalse( BankAccount.isEmailValid("ab@.com"));  // missing domain name
        assertFalse( BankAccount.isEmailValid("ab#cd@fh.com")); // invalid character (#)
        assertFalse( BankAccount.isEmailValid("ab-@ch.com")); //missing letter or number after special character
        assertFalse( BankAccount.isEmailValid("ab@cd.e")); // domain extension too short, border case
        assertFalse( BankAccount.isEmailValid("")); // invalid character (none), border case

        // valid emails
        assertTrue( BankAccount.isEmailValid("a@b.com")); // simple format
        assertTrue( BankAccount.isEmailValid("a-b@c.com")); // valid characters (dash pre @)
        assertTrue( BankAccount.isEmailValid("ab@ed.cc")); // another simple format
        assertTrue( BankAccount.isEmailValid("a12@gm.com")); // valid character (numbers)
        assertTrue( BankAccount.isEmailValid("am@mail-archive.com")); // valid character (dash post @)
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}