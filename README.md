<<<<<<< HEAD
# TicTacToe
A program for simulating a TicTacToe game between two players
=======
# Simpletron
A computer simulator using customized machine language

(Machine-Language Programming) 

Let’s create a computer called the Simpletron. As its name implies, it’s a simple, but powerful, machine. 
The Simpletron runs programs written in the only language it directly understands: Simpletron Machine Language (SML).
The Simpletron contains an accumulator—a special register in which information is put before the Simpletron uses that information in calculations or examines it in various ways. 
All the information in the Simpletron is handled in terms of words. 
A word is a signed four-digit decimal number, such as +3364, -1293, +0007 and -0001. 
The Simpletron is equipped with a 100-word memory, and these words are referenced by their location numbers 00, 01, …, 99.
Before running an SML program, we must load, or place, the program into memory. The first instruction (or statement) of every SML program is always placed in location 00. 
The simulator will start executing at this location.
Each instruction written in SML occupies one word of the Simpletron’s memory (and hence instructions are signed four-digit decimal numbers). 
We shall assume that the sign of an SML instruction is always plus, but the sign of a data word may be either plus or minus. 
Each location in the Simpletron’s memory may contain an instruction, a data value used by a program or an unused (and hence undefined) area of memory. 
The first two digits of each SML instruction are the operation code specifying the operation to be performed. SML operation codes are summarized below.

Operation code Meaning
Input/output operations:
final int READ = 10; Read a word from the keyboard into a specific location in memory
final int WRITE = 11; Write a word from a specific location in memory to the screen.

Load/store operations:
final int LOAD = 20; Load a word from a specific location in memory into the accumulator.
final int STORE = 21; Store a word from the accumulator into a specific location in memory.

Arithmetic operations:
final int ADD = 30; Add a word from a specific location in memory to the word in the accumulator (leave the result in the accumulator).
final int SUBTRACT = 31; Subtract a word from a specific location in memory from the word in the accumulator (leave the result in the accumulator).
final int DIVIDE = 32; Divide a word from a specific location in memory into the word in the accumulator (leave result in the accumulator).
final int MULTIPLY = 33; Multiply a word from a specific location in memory by the word in the accumulator (leave the result in the accumulator). 

Transfer-of-control operations:
final int BRANCH = 40; Branch to a specific location in memory.
final int BRANCHNEG = 41; Branch to a specific location in memory if the accumulator is negative.
final int BRANCHZERO = 42; Branch to a specific location in memory if the accumulator is zero.
final int HALT = 43; Halt. The program has completed its task.

The last two digits of an SML instruction are the operand—the address of the memory location containing the word to which the operation applies.


(Computer Simulator) 
In this project, you’re going to build your own computer. 
No, you’ll not be soldering components together. Rather, you’ll use the powerful technique of softwarebased simulation to create an object-oriented software model of the Simpletron above. 
Your Simpletron simulator will turn the computer you’re using into a Simpletron, and you’ll actually be able to run, test and debug the SML programs you wrote in Exercise 7.35.
When you run your Simpletron simulator, it should begin by displaying:

*** Welcome to Simpletron! ***
*** Please enter your program one instruction ***
*** (or data word) at a time. I will display ***
*** the location number and a question mark (?) ***
*** You then type the word for that location. ***
*** Type -99999 to stop entering your program. ***

Your application should simulate the memory of the Simpletron with a one-dimensional array memory that has 100 elements. 
Now assume that the simulator is running, and let’s examine the dialog
as we enter the program:
00 ? +1009
01 ? +1010
02 ? +2009
03 ? +3110
04 ? +4107
05 ? +1109
06 ? +4300
07 ? +1110
08 ? +4300
09 ? +0000
10 ? +0000
11 ? -99999

Your program should display the memory location followed by a question mark. Each value to the
right of a question mark is input by the user. When the sentinel value -99999 is input, the program
should display the following:

*** Program loading completed ***
*** Program execution begins ***

The SML program has now been placed (or loaded) in array memory. Now the Simpletron executes the SML program. 
Execution begins with the instruction in location 00 and, as in Java, continues sequentially, unless directed to some other part of the program by a transfer of control.
Use the variable accumulator to represent the accumulator register. Use the variable instructionCounter to keep track of the location in memory that contains the instruction being performed. Use the variable operationCode to indicate the operation currently being performed (i.e.,
the left two digits of the instruction word). Use the variable operand to indicate the memory location on which the current instruction operates. 
Thus, operand is the rightmost two digits of the instruction currently being performed. Do not execute instructions directly from memory. 
Rather, transfer the next instruction to be performed from memory to a variable called instructionRegister. 
Then “pick off ” the left two digits and place them in operationCode, and “pick off ” the right two digits and place them in operand. When the Simpletron begins execution, the special registers are all initialized to zero.


Implement your Simpletron simulator and run each of the SML programs you wrote in Exercise 7.35. If you desire, you may embellish SML with additional features and provide for these features in your simulator.
Your simulator should check for various types of errors. 
During the program-loading phase, for example, each number the user types into the Simpletron’s memory must be in the range -9999
to +9999. Your simulator should test that each number entered is in this range and, if not, keep prompting the user to re-enter the number until the user enters a correct number.
During the execution phase, your simulator should check for various serious errors, such as attempts to divide by zero, attempts to execute invalid operation codes, and accumulator overflows
(i.e., arithmetic operations resulting in values larger than +9999 or smaller than -9999). Such serious errors are called fatal errors. When a fatal error is detected, your simulator should display an error message, such as

*** Attempt to divide by zero ***
*** Simpletron execution abnormally terminated ***

and should display a full computer dump in the format we discussed previously. This treatment will help the user locate the error in the program.
>>>>>>> 7993c12 (Create README.md)
