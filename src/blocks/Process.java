package blocks;

public class Process extends MemoryBlock {
    public static int id = 0;

    public Process(int size) {
        super("Process" + id++, size);
    }
    public Process(String name, int size) {
        super(name, size);
        id++;
    }
    public Process(String name, int size, boolean copy) {
        super(name, size);
    }

    @Override
    public MemoryBlock copy() {
        return new Process(name, size, true);
    }

    @Override
    public String toString() {
        if(this.allocated) return "";
        else  return name + " couldn't be allocated";
    }
}
