package ds.project1task3;

/*
 * @author Sreemoyee Mukherjee
 *
 * This file is the Model component of the MVC, and it models the business
 * logic for the web application.  In this case, the business logic involves
 * keeping count of each selected option in a survey.
 */
public class Task3Model {
    private int countA, countB, countC, countD;

    // initialize each option count to 0
    public Task3Model() {
        countA = 0;
        countB = 0;
        countC = 0;
        countD = 0;
    }

    /**
     * Argument.
     *
     * @param answer The answer selected by user.
     */
    public void setResults(String answer){
        // check which option has been selected by the user and increment corresponding option count
        if(answer.equals("A")){
            countA++;
        }
        if(answer.equals("B")){
            countB++;
        }
        if(answer.equals("C")){
            countC++;
        }
        if(answer.equals("D")){
            countD++;
        }
    }

    // getters of all option counts
    public int getCountA(){
        return countA;
    }
    public int getCountB(){
        return countB;
    }
    public int getCountC(){
        return countC;
    }
    public int getCountD() {
        return countD;
    }

    // setters of all option counts
    public void setCountA(int countA) {
        this.countA = countA;
    }

    public void setCountB(int countB) {
        this.countB = countB;
    }

    public void setCountC(int countC) {
        this.countC = countC;
    }

    public void setCountD(int countD) {
        this.countD = countD;
    }
}
