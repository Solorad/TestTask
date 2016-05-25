package axiomsl.testtask.eventqueue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author emorenkov
 */
public class EventHandlerImpl implements EventHandler {
    private volatile boolean isInterrupted;

    private final List<MyEvent> eventQueue;
    private final Object mutex;

    public EventHandlerImpl() {
        this.eventQueue = Collections.synchronizedList(new LinkedList<>());
        this.isInterrupted = false;
        this.mutex = new Object();
    }


    public void addEvent(MyEvent event) throws Exception {
        if (!isInterrupted) {
            eventQueue.add(event);
            synchronized (mutex) {
                mutex.notify();
            }
        }
    }

    public void startHandler() throws Exception {
        while(!isInterrupted) {
            synchronized (mutex) {
                while(!isInterrupted && eventQueue.isEmpty()) {
                    mutex.wait();
                }
            }
            MyEvent firstEvent = eventQueue.remove(0);
            firstEvent.execute();
        }
    }

    public void stopHandler() throws Exception {
        isInterrupted = true;
        synchronized (mutex) {
            mutex.notify();
        }
    }
}
