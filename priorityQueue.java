import java.util.Scanner;

//Create variables
class task {
    public String job;
    public int priority;

    /* parametrized constructor */
    public task(String j, int p) {
        job = j;
        priority = p;
    }

    /** toString() **/
    public String toString() {
        String x = "Job: " + job + "\nPriority : " + priority;
        return x;
    }
}

/* to implement the priority queue */
class priority_queue {
    private task[] heap;
    private int heap_size;
    private int capacity;

    /* constructor */
    public priority_queue(int c) {
        capacity = c + 1;
        heap = new task[capacity];
        heap_size = 0;
    }

    /* to clear the priority queue */
    public void clear() {
        heap = new task[capacity];
        heap_size = 0;
    }

    /* to check if the function is empty */
    public boolean is_empty() {
        if (heap_size == 0)
            return true;
        else
            return false;
    }

    /* to check if the priority queue is full or not */
    public boolean is_full() {
        return heap_size == capacity - 1;
    }

    /* to return the size of the heap */
    public int size() {
        return heap_size;
    }

    /* to insert an element in the queue */
    public void insert(String job, int priority) {
        task n = new task(job, priority);

        heap[++heap_size] = n;
        int position = heap_size;
        while (position != 1 && n.priority > heap[position / 2].priority) {
            heap[position] = heap[position / 2];
            position /= 2;
        }
        heap[position] = n;
    }

    /* to remove a task from the queue */
    public task remove() {
        int parent1;
        int child1;
        task item1;
        task temp1;
        if (is_empty() == true) {
            System.out.println("Heap is empty");
            return null;
        }

        item1 = heap[1];
        temp1 = heap[heap_size--];

        parent1 = 1;
        child1 = 2;
        while (child1 <= heap_size) {
            if (child1 < heap_size && heap[child1].priority < heap[child1 + 1].priority)
                child1++;
            if (temp1.priority >= heap[child1].priority)
                break;

            heap[parent1] = heap[child1];
            parent1 = child1;
            child1 *= 2;
        }
        heap[parent1] = temp1;
        return item1;
    }
}

/** Class PriorityQueueTest **/
class Main {
    public static void main(String[] args) {
        priority_queue p = new priority_queue(10);
        p.insert("five", 5);
        p.insert("One", 1);
        p.insert("Ten", 10);
        p.insert("Eleven", 11);
        System.out.println("Size = " + p.size());
        while (!p.is_empty()) {
            System.out.println(p.remove());
        }
    }
}