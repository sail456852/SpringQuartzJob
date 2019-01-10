package udemy.cc;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/1/9<br/>
 * Time: 15:49<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        Message msg = new Message();
        Thread wt = new Thread(new Writer(msg));
        Thread rt = new Thread(new Reader(msg));
        wt.start();
        rt.start();
    }

}

class Message{
    private String message;
    private boolean empty = true;

    public synchronized String read() {
        while(empty){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = true;
        notifyAll();
        return message;
    }


    public synchronized void write(String msg) {
        while(!empty){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = false;
        this.message = msg;
        notifyAll();
    }
}

class Writer implements Runnable{
    private Message msg;

    public Writer(Message msg) {
        this.msg = msg;
    }

    @Override
    public  void  run() {
        String[] msgs = {
                "Message 1",
                "Message 2",
                "Message 3",
                "Message 4",
                "Message 5"
        };

        Random random = new Random();
        for (int i = 0; i < msgs.length; i++) {
            msg.write(msgs[i]);

            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        msg.write("Finished");
    }
}

class Reader implements Runnable{

    private Message msg;

    public Reader(Message msg) {
        this.msg = msg;
    }

    @Override
    public  void run() {
        Random random = new Random();
        for(String lmessage = msg.read(); !lmessage.equals("Finished"); lmessage = msg.read()){
            System.err.println("lmessage = " + lmessage);
            try { Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

