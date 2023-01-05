package blocks;

import java.util.Comparator;

public class BlockComparator implements Comparator<MemoryBlock> {
    @Override
    public int compare(MemoryBlock memoryBlock, MemoryBlock t1) {
        if (memoryBlock.size > t1.size) return 1;
        else if(memoryBlock.size == t1.size) return 0;
        else return -1;
    }
}
