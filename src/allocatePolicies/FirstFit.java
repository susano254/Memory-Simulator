package allocatePolicies;

import blocks.MemoryBlock;

import java.util.LinkedList;

public class FirstFit extends MemorySimulator {
    @Override
    public int search(MemoryBlock process, LinkedList<MemoryBlock> partitions) {
        for(int i = 0; i < partitions.size(); i++){
            //get the current searched partition
            MemoryBlock currentPartition = partitions.get(i);
            //if it's already allocated then skip it
            if(currentPartition.allocated) continue;
            //else return its index
            if(process.fitsIn(currentPartition))
                return i;
        }
        return -1;
    }
}
