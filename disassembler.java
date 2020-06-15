//David William Nartey
//CS 475 Assignment 3
import java.lang.*;

class code{
    //OPCODE
    String noOpCode = "0000";//0x0
    String ld = "0001";//0x1
    String st = "0010";//0x2
    String br = "0011";//0x3
    String bsr = "0100";//0x4
    String brz = "0101";//0x5
    String bnz = "0110";//0x6
    String brn = "0111";//0x7
    String bnn = "1000";//0x8

    //SubOPCODE
    String nop = "00000";//0x00
    String ldi = "00001";//0x01
    String sti = "00010";//0x02
    String add = "00011";//0x03
    String sub = "00100";//0x04
    String and = "00101";//0x05
    String or = "00110";//0x06
    String xor = "00111";//0x07
    String shl = "01000";//0x08
    String sal = "01001";//0x09
    String shr = "01010";//0x0a
    String sar = "01011";//0x0b
    String rts = "10000";//0x10
    String halt = "11111";//0x1f
}
public class disassembler {
    //Variable Declaration
    static String opCode,register,address,rA,rB,rC,subOpcode,binary;
    static String r1,r2,r3,r4;
    static String input =  "10 80 0a " +
            "11 00 0b " +
            "00 88 63 " +
            "01 94 88 " +
            "72 00 07 " +
            "22 00 0c " +
            "00 00 1f " +
            "00 10 84 " +
            "22 00 0c " +
            "00 00 1f " +
            "00 00 00 " +
            "00 00 00 " +
            "00 00 00";
    static String[] inputSplit = input.split(" ");
    static int[] inputNumbers = new int[inputSplit.length];
    static String[] firstEight = new String[13];
    static String[] secondEight = new String[13];
    static String[] thirdEight = new String[13];
    static String[] instructionSet = new String[13];
    //static String[] registerAddress = new String[29];
    static code x = new code();

    //appends 0 to binary if length is less than 8
    public static String appendCode(String code){
        for(int i = 0; i<code.length(); i++){
            if (!(code.length()%8 == 0)){
                code = "0"+code;
            }
        }
        //System.out.println("Final "+code);
        return code;
    }

    //convert input in hex to binary and calls append to store in arrays
    public static void convert(int pos,String [] placeholder){
        int j = 0;
        for(int i = pos; i < inputSplit.length; i+=3) {
            inputNumbers[i] = Integer.parseInt(inputSplit[i], 16);
            binary = Integer.toBinaryString(inputNumbers[i]);
            placeholder[j] = appendCode(binary);
            j++;
        }
    }

    //combines arrays into single instruction set
    public static void combine(){
        for(int i =0;i<firstEight.length;i++){
            instructionSet[i] = firstEight[i] + secondEight[i] + thirdEight[i];
            //System.out.println(instructionSet[i]);
        }
    }

    //Prints the non converted instruction set
    public static void printInstructionSet(){
        for (String s : instructionSet) {
            String op = s.substring(0,4);
            if((op.equals(x.noOpCode))){
                opCode = s.substring(0,4);
                rA = s.substring(4,9);
                rB = s.substring(9,14);
                rC = s.substring(14,19);
                subOpcode = s.substring(19);
                System.out.print(opCode + " ");
                System.out.print(rA + " ");
                System.out.print(rB + " ");
                System.out.print(rC + " ");
                System.out.println(subOpcode);
            }
            else{
                opCode = s.substring(0,4);
                register = s.substring(4,9);
                address = s.substring(9);
                System.out.print(opCode + " ");
                System.out.print(register + " ");
                System.out.println(address);
            }
        }

    }

    //function for displaying the converted instruction set for when the opcode is not 0
    public static void noOpcode(String name, String op){
        if(opCode.equals(op)){
            System.out.print(name + " ");
            System.out.print(register + " ");
            System.out.println(address);
        }
    }

    //function for displaying the converted instruction set for when the opcode is 0
    public static void subOpcode(String name, String sub){
        if(subOpcode.equals(sub)){
            System.out.print(opCode + " ");
            System.out.print(rA + " ");
            System.out.print(rB + " ");
            System.out.print(rC + " ");
            System.out.println(name);
        }
    }

    //saving the registers
    public static void saveRegisters(){
        /*System.out.println(s);
        for(int i = 0; i<instructionSet.length;i++){
            opCode = instructionSet[i].substring(0,4);
            if((opCode.equals(x.noOpCode))){
                rA = instructionSet[i].substring(4,9);
                rB = instructionSet[i].substring(9,14);
                rC = instructionSet[i].substring(14,19);
                registerAddress[i] = rA + " " + rB + " "+ rC;
                //registerAddress[i+1] = rB;
                //registerAddress[i+2] = rC;
            }
            else {
                register = instructionSet[i].substring(4, 9);
                registerAddress[i] = register;
            }
            System.out.println(registerAddress[i]);
        }*/
        r1 = "00001";
        r2 = "00010";
        r3 = "00011";
        r4 = "00100";
    }

    //functions that displays appropriate registers
    public static void opHelper(String exp){
        if(exp.equals(r1))
            register = "r1";
        if(exp.equals(r2))
            register = "r2";
        if(exp.equals(r3))
            register = "r2";
        if(exp.equals(r3))
            register = "r3";
        if(exp.equals(r4))
            register = "r4";
    }

    //calls below functions and displays appropriate registers
    public static void replaceBinary(String exp1, String exp2, String exp3){
        subHelperA(exp1);
        subHelperB(exp2);
        subHelperC(exp3);
    }

    //helper functions for setting register names. Repetitive
    public static void subHelperA(String reg){
        if(reg.equals(r1))
            rA = "r1";
        if(reg.equals(r2))
            rA = "r2";
        if(reg.equals(r3))
            rA = "r2";
        if(reg.equals(r3))
            rA = "r3";
        if(reg.equals(r4))
            rA = "r4";
    }

    public static void subHelperB(String reg){
        if(reg.equals(r1))
            rB = "r1";
        if(reg.equals(r2))
            rB = "r2";
        if(reg.equals(r3))
            rB = "r2";
        if(reg.equals(r3))
            rB = "r3";
        if(reg.equals(r4))
            rB = "r4";
    }

    public static void subHelperC(String reg){
        if(reg.equals(r1))
            rC = "r1";
        if(reg.equals(r2))
            rC = "r2";
        if(reg.equals(r3))
            rC = "r2";
        if(reg.equals(r3))
            rC = "r3";
        if(reg.equals(r4))
            rC = "r4";
    }

    public static void main(String[] args) {
        /*
        //Code to take input from commandline. Unnecessary for scope of assignment
        String rand="";
        for (int i=0; i<args.length; i+=2){
            if(args[i].equals("-i")){
                rand = args[i + 1];
            }
        }
        int x= input.length() %6;
        if(!(x==0)){
            System.out.println("invalid Code");
            System.exit(0);
        }
        else{
            for(int i = 0; i<input.length();i+=2){
                System.out.println(input.substring(i,i+2));
                rand = rand + input.substring(i,i+2) + " ";
            }
        }
        System.out.println(rand);
        input = "";
        input = rand;
        System.out.println(input);
        */


        convert(0,firstEight);
        convert(1,secondEight);
        convert(2,thirdEight);
        combine();
        System.out.println("Instruction Set in Binary According to Opcode");
        printInstructionSet();
        saveRegisters();
        System.out.println("\nInstruction set converted");
        for (String s : instructionSet) {
            //System.out.println(s);
            opCode = s.substring(0, 4);
            if ((opCode.equals(x.noOpCode))) {
                rA = s.substring(4, 9);
                rB = s.substring(9, 14);
                rC = s.substring(14, 19);
                subOpcode = s.substring(19);
                replaceBinary(rA,rB,rC);
                subOpcode("nop", x.nop);
                subOpcode("ldi", x.ldi);
                subOpcode("sti", x.sti);
                subOpcode("add", x.add);
                subOpcode("sub", x.sub);
                subOpcode("and", x.and);
                subOpcode("or", x.or);
                subOpcode("xor", x.xor);
                subOpcode("shl", x.shl);
                subOpcode("sal", x.sal);
                subOpcode("shr", x.shr);
                subOpcode("sar", x.sar);
                subOpcode("rts", x.rts);
                subOpcode("halt", x.halt);
            } else {
                register = s.substring(4, 9);
                address = s.substring(9);
                opHelper(r1);
                opHelper(r2);
                opHelper(r3);
                opHelper(r4);
                noOpcode("ld", x.ld);
                noOpcode("st", x.st);
                noOpcode("br", x.br);
                noOpcode("bsr", x.bsr);
                noOpcode("brz", x.brz);
                noOpcode("bnz", x.bnz);
                noOpcode("brn", x.brn);
                noOpcode("bnn", x.bnn);
            }
        }
    }
}

