package allocatePolicies;

import blocks.MemoryBlock;
import blocks.Partition;
import blocks.Process;

import java.util.LinkedList;
import java.util.Scanner;

public abstract class MemorySimulator {
    //run method is the init code of all MemorySimulators
    public void run(LinkedList<MemoryBlock> pProcesses, LinkedList<MemoryBlock> pPartitions) {
        //deep copy both lists to not override the inputs
        //reset the ids to produce the same names each time
        Process.id = pProcesses.size();
        Partition.id = pPartitions.size();
        LinkedList<MemoryBlock> processes = deepCopy(pProcesses);
        LinkedList<MemoryBlock> partitions = deepCopy(pPartitions);

        //allocate processes and print them
        simulate(processes, partitions);

        //compact if needed
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to compact? 1.yes\t2.no");
        if(scanner.nextInt() == 1){
            //if yes then compact processes and allocate memory for the remaining processes (if any)
            compact(partitions);
            simulate(processes, partitions);
        }
    }

    //for every process we need 3 steps
    //1- search for a fit partition (depends on if is bestFit, worstFit or firstFit) that why its abstract method
    //2- allocate the process in the returned partition
    //3- update partitions (embedded in the allocate method since it's trivial)
    //at the end just print the partitions with their allocated process
    //also print any non allocated processes
    public void simulate(LinkedList<MemoryBlock> processes, LinkedList<MemoryBlock> partitions) {
        for(int i = 0; i < processes.size(); i++){
            MemoryBlock currentProcess = processes.get(i);
            if(currentProcess.allocated) continue;
            //search for a partition that first fits this process and gets its index
            int partitionIndex = search(currentProcess, partitions);
            //allocate the needed partition size for the process and update the list if a suitable partition found
            if(partitionIndex != -1) {
                allocate(currentProcess, partitions, partitionIndex);
                currentProcess.allocated = true;
            }
        }

        //print partitions
        System.out.println(partitions);
        //print only unallocated processes
        for(int i = 0; i < processes.size(); i++) {
            if (!processes.get(i).allocated)
                System.out.println(processes.get(i).name + " couldn't be allocated");
        }
    }

    //allocate a given process to a given partition and update the partitions list
    public void allocate(MemoryBlock process, LinkedList<MemoryBlock> partitions, int index){
        Partition partition = (Partition) partitions.get(index);
        if(process.fitsIn(partition)) {
            //save the remaining size after allocation
            int leftSize = partition.size - process.size;
            //shrink the partition size to avoid internal fragment
            partition.size = process.size;
            //update the partition status
            partition.allocated = true;
            partition.allocatedProcess = (Process) process;
            //finally if remains any size from the allocation make a new partition in place of the shrank size
            if(leftSize > 0)
                partitions.add(index+1, new Partition(leftSize));
        }
    }

    //get rid of external fragments
    public void compact(LinkedList<MemoryBlock> partitions){
        //sum all sizes of external fragments
        int sumSize = 0;
        for(int i = 0; i < partitions.size(); i++){
            Partition partition = (Partition) partitions.get(i);
            //if it is an external fragment save its size and delete it
            if(partition.allocatedProcess == null) {
                sumSize += partition.size;
                partitions.remove(i);
                //don't increment next iteration since next element already at current index
                i--;
            }
        }
        partitions.add(new Partition(sumSize));
    }

    //just deep copy a given list
    private LinkedList<MemoryBlock> deepCopy(LinkedList<MemoryBlock> list){
        LinkedList<MemoryBlock> newList = new LinkedList<>();
        for(int i = 0; i < list.size(); i++)
            newList.add(list.get(i).copy());

        return newList;
    }

    abstract int search(MemoryBlock process, LinkedList<MemoryBlock> partitions);
}
