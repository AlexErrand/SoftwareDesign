import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {
    @Test
    void setAndGetFunds(){
        BankAccount awesome = new BankAccount("Alex", 0.0F);
        awesome.setBalanceUSD(125.50F);
        assertEquals(125.50F, awesome.getBalanceUSD());

    }

    @Test
    void closeNegativeBalance(){
        BankAccount awesome = new BankAccount("Alex", 0.0F);
        awesome.setBalanceUSD(-100.1F);
        float[] correctArr =  {0,0,0,0,0,0,0,0};
        //System.out.print(Arrays.toString(correctArr));
        assertEquals(Arrays.toString(correctArr), Arrays.toString(awesome.closeAccount(awesome.getBalanceUSD())));
    }

    @Test
    void closeAccountTest(){
        BankAccount awesome = new BankAccount("Alex", 125.25F);
        float[] correctArr =  {6,0,1,0,1,0,0,0};
        //System.out.print(Arrays.toString(correctArr));
        assertEquals(Arrays.toString(correctArr), Arrays.toString(awesome.closeAccount(awesome.getBalanceUSD())));

    }

    @Test
    void closeAccountCentsOnly(){
        BankAccount awesome = new BankAccount("Alex", 0.55F);
        float[] correctArr =  {0,0,0,0,2,0,1,0};
        //System.out.print(Arrays.toString(correctArr));
        assertEquals(Arrays.toString(correctArr), Arrays.toString(awesome.closeAccount(awesome.getBalanceUSD())));
    }

    @Test
    void closeAccountCashAndCents(){
        BankAccount awesome = new BankAccount("Alex", 151.59F);
        float[] correctArr =  {7,1,0,1,2,0,1,4};
        //System.out.print(Arrays.toString(correctArr));
        assertEquals(Arrays.toString(correctArr), Arrays.toString(awesome.closeAccount(awesome.getBalanceUSD())));
    }
    @Test
    void closeAccountCashAndCentsAllBills(){
        BankAccount awesome = new BankAccount("Alex", 197.69F);
        float[] correctArr =  {9,1,1,2,2,1,1,4};
        //System.out.print(Arrays.toString(correctArr));
        assertEquals(Arrays.toString(correctArr), Arrays.toString(awesome.closeAccount(awesome.getBalanceUSD())));
    }

    @Test
    void withdrawSWDDollarsOnly(){
        BankAccount awesome = new BankAccount("Alex", 190);
        float conversion = 2.0F;
        float[] correctArr =  {4,2,0,0,0,0,0,0,130};
        assertEquals(Arrays.toString(correctArr), Arrays.toString(awesome.withdrawSWD(awesome.getBalanceUSD(), (120F), conversion)));
    }

    @Test
    void withdrawSWDCentsOnly(){
        BankAccount awesome = new BankAccount("Alex", 0.69F);
        float conversion = 1.0F;
        float[] correctArr =  {0,0,0,0,1,0,0,0,0.49F};
        assertEquals(Arrays.toString(correctArr), Arrays.toString(awesome.withdrawSWD(awesome.getBalanceUSD(), (0.20F), conversion)));
    }

    @Test
    void withdrawSWDDollarsAndCents(){
        BankAccount awesome = new BankAccount("Alex", 190.69F);
        float conversion = 1.0F;
        float[] correctArr =  {4,2,0,0,1,0,0,0,70.49F};
        assertEquals(Arrays.toString(correctArr), Arrays.toString(awesome.withdrawSWD(awesome.getBalanceUSD(), (120.20F), conversion)));
    }




}
