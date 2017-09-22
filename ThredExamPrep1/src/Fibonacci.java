
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class Fibonacci
{
    private static long fib(long n)
    {
        if ((n==0) || (n == 1))
          {
            return n;
          }
        else
          {
            return (fib(n-1)+ fib(n-2));
            
          }
    }
    
    public static void main(String[] args)
    {
        ExecutorService ex = Executors.newFixedThreadPool(11);
        
        ArrayList<Long> array = new ArrayList(Arrays.asList(4l, 5l, 8l, 12l, 21l, 22l, 34l, 35l, 36l, 37l, 42l));
        ArrayBlockingQueue<Long> s1 = new ArrayBlockingQueue(11, true, array);
        //ArrayBlockingQueue<Long> s1 = new ArrayBlockingQueue(10);
        ArrayBlockingQueue<Long> s2 = new ArrayBlockingQueue(11);
        
       
        ex.execute(new FibonaccyProducer(s1 , s2));
        ex.execute(new FibonaccyProducer(s1 , s2));
        ex.execute(new FibonaccyProducer(s1 , s2));
        ex.execute(new FibonaccyProducer(s1 , s2));
        FibonaccyConsumer sc = new FibonaccyConsumer(s2); 
        ex.execute(sc);
        
        sc.getSum();
        
        ex.shutdown();
       
 
    }
}
