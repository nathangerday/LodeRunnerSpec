package contracts;

public class Checker{
    private Checker(){}

    public static boolean implication(boolean leftCond, boolean rightCond){
        return !leftCond || rightCond;
    }
}