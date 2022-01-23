package elfmodifiers;

public interface Visitor {
     /**
      * Method visits BlackElf elf
      * @param elf elf to be visited
      */
     void visit(BlackElf elf);

     /**
      * Method visits PinkElf elf
      * @param elf elf to be visited
      */
     void visit(PinkElf elf);

     /**
      * Method visits YellowElf elf
      * @param elf elf to be visited
      */
     void visit(YellowElf elf);

}
