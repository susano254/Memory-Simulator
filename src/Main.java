import allocatePolicies.*;
import blocks.MemoryBlock;
import blocks.Partition;
import blocks.Process;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<MemoryBlock> partitions;
        LinkedList<MemoryBlock> processes;
        //override the processes linkedList toString method to print only non allocated processes at the end
        //just like in the assignment demonstration
        partitions = new LinkedList<>();
        processes = new LinkedList<>();

        partitions.add(new Partition("Partition0", 90));
        partitions.add(new Partition("Partition1", 20));
        partitions.add(new Partition("Partition2", 5));
        partitions.add(new Partition("Partition3", 30));
        partitions.add(new Partition("Partition4", 120));
        partitions.add(new Partition("Partition5", 80));

        processes.add(new Process("Process1", 15));
        processes.add(new Process("Process2", 90));
        processes.add(new Process("Process3", 30));
        processes.add(new Process("Process4", 100));

        MemorySimulator firstFit = new FirstFit();
        MemorySimulator bestFit = new BestFit();
        MemorySimulator worstFit = new WorstFit();

        firstFit.run(processes, partitions);
        bestFit.run(processes, partitions);
        worstFit.run(processes, partitions);
    }
}