package blocks;

public abstract class MemoryBlock {
    public String name;
    public int size;
    public boolean allocated = false;

    public MemoryBlock(MemoryBlock p){
        this.name = p.name;
        this.size = p.size;
    }
    public MemoryBlock(String name, int size){
        this.name = name;
        this.size = size;
    }
    public boolean fitsIn(MemoryBlock block){
        if(this.size <= block.size) return true;
        else return false;
    }

    public abstract MemoryBlock copy();
}
