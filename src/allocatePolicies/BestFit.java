package allocatePolicies;

import blocks.BlockComparator;
import blocks.MemoryBlock;

import java.util.LinkedList;

public class BestFit extends MemorySimulator {
    @Override
    public int search(MemoryBlock process, LinkedList<MemoryBlock> partitions) {
        //find min size that fits this process
        //first sort a copy of the partitions list to not overwrite the original list
        LinkedList<MemoryBlock> copyPartitions = new LinkedList<>(partitions);
        copyPartitions.sort(new BlockComparator());
        //return the first partition that fits since its sorted
        for(int i = 0; i < copyPartitions.size(); i++){
            //get the current searched partition
            MemoryBlock currentPartition =  copyPartitions.get(i);
            //if it's already allocated then skip it
            if(currentPartition.allocated) continue;
            //else return its index
            if(process.fitsIn(currentPartition))
                return partitions.indexOf(currentPartition);
        }
        return -1;
    }
}
