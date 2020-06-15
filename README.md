David William Nartey

The program can be run by first creating the class file with the command: 
"javac disassembler.java".
Then by running the application with : "java disassembler". 
The output of the application will be the input of the hex program converted 
to binary and partitioned according to the type of code that was taken in.
Example, if the bits 20-23 represent an opcode, the program will output 
the binary in the form of "0001 00001 000000000001010" where bits 20-23 are 
the opcode, the next five digits are the register and the remaining digits 
are the address since the executable file in bit-endian. If bits 20-23 are 
an opcode with that is 0, the binary will be in the form of "0000 00001 00010 00011 00011" 
where bits 5-19 represent the three registers and 4 to 0 is the subopcode and 
the instructions. 
The program also converts the opcode and subopcode to the instruction that they represent.
The program also keeps track of the registers in use:
The final output will be:<br />
ld r4 000000000001010<br />
ld r4 000000000001011<br />
0000 r1 r2 r3 add<br />
0000 r3 00101 r4 shl<br />
brn r4 000000000000111<br />
st r4 000000000001100<br />
0000 00000 00000 00000 halt<br />
0000 00000 r4 r4 sub<br />
st r4 000000000001100<br />
0000 00000 00000 00000 halt<br />
0000 00000 00000 00000 nop<br />
0000 00000 00000 00000 nop<br />
0000 00000 00000 00000 nop<br />

The hex input can be replaced with any other hex input of the same formatting and 
the program will convert and ouput the appropriate instruction set as well. 
The program operates under the assumption that the registers are already known. 
The program uses Strings to store the instructions since saving them in int would result 
in the 0s appended to the set of 8 bits being removed. SubStrings were used to extract 
the opcode and the corresponding partitions instead of bit wise operators 
since the program dealt with strings. Comments placed in code to explain what 
different functions do. There is code for taking arguments from the command line and 
evaluating the input as the hex code but that does not seem necessary for the scope 
of the application since it is meant to work for the hex code given. It can be tested 
with the hex code by replacing the input with hex code in the same formatting. The 
registers may have to be updated as well. 

Tested Conversion to binary.
Input: 10 80 0a <br />
Output: 00010000 10000000 00001010<br />
Input: 11 00 0b<br />
Output: 00010001 00000000 00001011<br />
Test Passed<br />

Tested partitioning with different opcodes.<br />
Input: 000100001000000000001010<br />
Output:0001 00001 000000000001010<br />
Input:000000001000100001100011<br />
Ouput:0000 00001 00010 00011 00011<br />
Test Passed <br />

Tested appending 0s to the beginning of a binary if the length mod 8 was not 0.<br />
Input: 100001<br />
Ouput: 00010001<br />
Input: 100010<br />
Output: 000100010<br />
Test passed<br />

Tested printing instructions in binary with partitions<br />
Output: 0001 00001 000000000001010<br />
0001 00010 000000000001011<br />
0000 00001 00010 00011 00011<br />
0000 00011 00101 00100 01000<br />
0111 00100 000000000000111<br />
0010 00100 000000000001100<br />
0000 00000 00000 00000 11111<br />
0000 00000 00100 00100 00100<br />
0010 00100 000000000001100<br />
0000 00000 00000 00000 11111<br />
0000 00000 00000 00000 00000<br />
0000 00000 00000 00000 00000<br />
0000 00000 00000 00000 00000<br />
Test Passed<br />

Tested conversion from binary to instruction set<br />
Test Passed. Similar to test below.<br />
Tested in tux and the output remained the same. <br /> 
Output:<br />
ld r4 000000000001010<br />
ld r4 000000000001011<br />
0000 r1 r2 r3 add<br />
0000 r3 00101 r4 shl<br />
brn r4 000000000000111<br />
st r4 000000000001100<br />
0000 00000 00000 00000 halt<br />
0000 00000 r4 r4 sub<br />
st r4 000000000001100<br />
0000 00000 00000 00000 halt<br />
0000 00000 00000 00000 nop<br />
0000 00000 00000 00000 nop<br />
0000 00000 00000 00000 nop<br />
Test Passed<br />


Explanation of what the instructino set entails:
ld x,r1         -> load x into register 1<br />
ld y,r2         -> load y into register 2<br />
add r1,r2,r4    -> add the values in register 1 and register 2 into
                register 3<br />
shl r3,5,r4     -> shift register 3 5 times and put in register 4<br />
brn r4,skip     -> branch if negative register 4<br />
halt            -> ignores register<br />
sub r0,r4,r4    -> if negative, subtract register 4 from register 0 
                and store new value in register 4<br />
st r4,z         -> store from register 4 to memory into  z<br />
halt            -> ignores register<br />
x data 0        -> data in x<br />
y data 0        -> data in y<br />
z data 0        -> data in z<br />