
package threadsquestao1;


public class Threads extends Thread{
    private String nome;
    
    public Threads(String nome){
        this.nome = nome;
    }
    
    @Override
    public void run(){
        exibirContagem();
    }
    
    public void exibirContagem(){
    for(int i=1; i<=5; i++){
        System.out.println("["+ nome +"] = " + i);
            try{
               sleep(500);
            }catch(InterruptedException ex){
               System.out.println(ex.getMessage());
            }
        }
    }
}

