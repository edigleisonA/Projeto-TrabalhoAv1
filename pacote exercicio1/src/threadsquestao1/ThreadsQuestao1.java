
package threadsquestao1;


public class ThreadsQuestao1 {

   
    public static void main(String[] args) {
        Threads t1 = new Threads("T1");
        Threads t2 = new Threads("T2");
        Threads t3 = new Threads("T3");
        Threads t4 = new Threads("T4");
        Threads t5 = new Threads("T5");
               
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
    
}
