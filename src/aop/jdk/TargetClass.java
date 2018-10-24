package aop.jdk;

/**
 * the target object to be proxied
 */
public class TargetClass implements TargetInterface{

    public int calculator(int a, int b){
        if(b != 0 ){
            return a / b;
        }else{
            return 0;
        }
    }
}
