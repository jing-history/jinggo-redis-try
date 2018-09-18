package wang.jinggo.tutorial.wwj.ch05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wang.jinggo.synchronize.NotifyDataBase;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

/**
 * @author wangyj
 * @description
 * @create 2018-09-17 10:18
 **/
public class EventQueue2 {

    private final static Logger LOG =  LoggerFactory.getLogger(NotifyDataBase.class);

    private final int max;
    private final int DEFAULT_MAX_EVENT = 50;

    static class Event{}

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    public EventQueue2(){
        max = DEFAULT_MAX_EVENT;
    }
    public EventQueue2(int max){
        this.max = max;
    }

    public void offer(Event event){
        synchronized (eventQueue){
            while (eventQueue.size() >= max){
                try {
                    console(" the queue is full. ");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            console(" the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    public Event take(){
        synchronized (eventQueue){
            while (eventQueue.isEmpty()){
                try {
                    console(" the queue is empty. ");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notifyAll();
            console(" the event " + event + " is handled.");
            return event;
        }
    }

    private void console(String message){
        System.out.printf("%s:%s\n", currentThread().getName(), message);
    }
}
