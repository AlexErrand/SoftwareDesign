import java.text.DecimalFormat;

public class BankAccount {
    private String userName;
    private float balanceUSD;

    public BankAccount(String userName, float balanceUSD) {

        this.balanceUSD = balanceUSD;
        this.userName = userName;

    }

    /**getUserName retrieves the username of the object
     * @return userName
     */

    public String getUserName() {
        return userName;
    }

    /**setUserName sets the username of the object
     * @return this.userName = userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**getBalanceUSD retrieves the balance of the object
     * @return balanceUSD
     */

    public float getBalanceUSD() {
        return balanceUSD;
    }

    /**setBalanceUSD retrieves the username of the object
     * @return this.balanceUSD
     */
    public void setBalanceUSD(float balanceUSD) {
        this.balanceUSD = balanceUSD;
    }

    /** closeAccount closes an account for a user and returns their USD balance with
     * the amount per remaining unit
     *
     * @param balanceUSD the balance of someone's account
     *
     * @return the calculated final value of the closed bank account
     */

    public float[] closeAccount(float balanceUSD) {
        float[] currencyArr = new float[8];
        float cents = (balanceUSD - (int)(balanceUSD)); //separate variable into cents and whole dollars
        cents = (float) (Math.round((cents)*Math.pow(10,3))/Math.pow(10,3));
        int uBalance = (int) balanceUSD;

        if (uBalance / 20 >= 1) { //checking if 20 dollar bills can be used.
            currencyArr[0] += uBalance / 20;
            uBalance = (int) (uBalance - (currencyArr[0]*20));
        }

        if (uBalance / 10 >= 1) { //checking if 10 dollar bills can be used
            currencyArr[1] += uBalance / 10;
            uBalance = (int) (uBalance - (currencyArr[1]*10));
        }

        if (uBalance / 5 >= 1) { //checking if 5 dollar bills can be used
            currencyArr[2] += uBalance / 5;
            uBalance = (int) (uBalance - (currencyArr[2]*5));
        }

        if (uBalance >= 1) { //checking if any whole dollars remain
            currencyArr[3] += uBalance;
        }

        if (cents / 0.25 >= 1) { //checking if any quarters (25 cents) remain
            currencyArr[4] += (int) (cents/0.25);
            cents = (float) (Math.round((cents - (currencyArr[4])*0.25)*Math.pow(10,3))/Math.pow(10,3));
        }

        if (cents / 0.1 >= 1) { //checking if any dimes (10 cents) remain
            currencyArr[5] += (int)(cents/0.1);
            cents = (float) (Math.round((cents - (currencyArr[5])*0.1)*Math.pow(10,3))/Math.pow(10,3));
        }

        if (cents / 0.05 >= 1) { //checking if any nickels (5 cents) remain
            currencyArr[6] += (int)(cents/0.05);
            cents = (float) (Math.round((cents - (currencyArr[6])*0.05)*Math.pow(10,3))/Math.pow(10,3));
        }


        if (cents / 0.01 >= 1) { //checking if any single cents (pennies) remain
            currencyArr[7] += (cents/0.01);

        }

        return currencyArr;
        }

    /**
     *
     * @param balanceUSD
     * @param swdRequest
     * @param conversionRate
     * @return withdrawArr, an array of each amount of withdrawable currency, cast to an integer
     */
    public float[] withdrawSWD(float balanceUSD, float swdRequest, float conversionRate) {
        float[] withdrawArr = new float[9]; //new array with 8 SWD currency deposits and total amount of USD left to be returned in the last slot
        DecimalFormat dForm = new DecimalFormat("0.00");

        if (balanceUSD >= swdRequest/conversionRate){ //checks if the exchange to the amount in US dollars and ensures there is enough balance for the withdrawal

            float swdCents = (float) (Math.round((swdRequest - (int)(swdRequest))*Math.pow(10,3))/Math.pow(10,3));


            int swdWithdraw = (int) ((swdRequest - swdCents)); //separate dollars and cents to put into the array for future use

            withdrawArr[8] = ((balanceUSD*conversionRate) - (swdWithdraw+swdCents))/conversionRate;
            withdrawArr[8] = Float.parseFloat(dForm.format(withdrawArr[8]));

            if (swdWithdraw / 25 >= 1) { //checking if 25 dollar bills can be used
                withdrawArr[0] += swdWithdraw / 25;
                swdWithdraw = (int) (swdWithdraw - (withdrawArr[0]*25));
            }

            if (swdWithdraw / 10 >= 1) { //checking if 10 dollar bills can be used, duplicate code is here due to same conditions for SWD and USD
                withdrawArr[1] += swdWithdraw / 10;
                swdWithdraw = (int) (swdWithdraw - (withdrawArr[1]*10));
            }

            if (swdWithdraw / 5 >= 1) { //checking if 5 dollar bills can be used
                withdrawArr[2] += swdWithdraw / 5;
                swdWithdraw = (int) (swdWithdraw - (withdrawArr[2]*5));
            }

            if (swdWithdraw >= 1) { //checking if any whole dollars remain
                withdrawArr[3] += swdWithdraw;
            }

            if (swdCents / 0.2 >= 1) { //checking if any quarters (25 cents) remain
                withdrawArr[4] += (int) (swdCents/0.2);
                swdCents = (float) (Math.round((swdCents - (withdrawArr[4])*0.2)*Math.pow(10,3))/Math.pow(10,3));
            }

            if (swdCents / 0.08 >= 1) { //checking if any dimes (10 cents) remain
                withdrawArr[5] += (int)(swdCents/0.08);
                swdCents = (float) (Math.round((swdCents - (withdrawArr[5])*0.08)*Math.pow(10,3))/Math.pow(10,3));
            }

            if (swdCents / 0.05 >= 1) { //checking if any nickels (5 cents) remain
                withdrawArr[6] += (int)(swdCents/0.05);
                swdCents = (float) (Math.round((swdCents - (withdrawArr[6])*0.05)*Math.pow(10,3))/Math.pow(10,3));
            }

            if (swdCents / 0.01 >= 1) { //checking if any single cents (pennies) remain
                withdrawArr[7] += (swdCents/0.01);
            }
        }
        return withdrawArr;
    }
}
