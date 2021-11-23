package simpletron;

import java.util.Scanner;


public class Simpletron
{
    private static final Scanner input = new Scanner(System.in);
    private static Throwable exception;
    
    public static void main(String[] args)
    {
        float[] memory = new float[1000];
        int counter = 0;
        
                
        System.out.println("*** Welcome to Simpletron! ***\n"
                + "*** Please enter your program one instruction ***\n"
                + "*** (or data word) at a time. I will display  ***\n"
                + "*** the location number and a question mark (?) ***\n"
                + "*** You then type the word for that location. ***\n"
                + "*** Type -99999 to stop entering the program. ***\n");
        
        System.out.printf("%03d ? +", counter);
        memory[counter] = input.nextInt();
        
        while(memory[counter] != -999999)
        {
            if (memory[counter] < -99999 || memory[counter] > 99999)
            {
                while (memory[counter] < -99999 | memory[counter] > 99999)
                {
                    System.out.printf("%03d ? +", counter);
                    memory[counter] = input.nextInt();
                }
            }
            
            counter++;
            System.out.printf("%03d ? +", counter);
            memory[counter] = input.nextInt();
        }

        System.out.println("\n*** Program loading completed ***\n"
                + "*** Program execution begins  ***\n");

        Executor(memory, counter);
    }
    
    public static void Executor(float[] memory, int counter)
    {
        float accumulator = 0;
        int instructionCounter = 0;
        float instructionRegister = 0;
        int operationCode = 0;
        int operand = 0;
        boolean error = false;
        
        for (; instructionCounter <= counter; )
        {
            instructionRegister = memory[instructionCounter];
            
            operationCode = (int) instructionRegister /1000;
            operand = (int) instructionRegister % 1000;
            instructionCounter++;
            
            switch(operationCode)
            {
                case 10: //READ
                    System.out.print("Enter an integer: ");
                    memory[operand] = input.nextFloat();
                    break;
                    
                case 11: //WRITE
                    System.out.printf("%.2f\n", memory[operand]);
                    break;
                    
                case 12: //NEW LINE
                    System.out.println();
                    break;
                    
                case 20: //LOAD
                    accumulator = memory[operand];
                    break;
                    
                case 21: //STORE
                    memory[operand] = accumulator;
                    break;
                    
                case 30: //ADD
                        accumulator += memory[operand];
                        //Exception handling of accumulator overflow
                        if (accumulator < -99999 || accumulator > 99999) 
                        {
                            error = true;
                            throw new IllegalArgumentException(
                            "*** Value greater than range (-99999 - +99999) ***\n"
                            + "*** TimpletrÖn execution abnormally terminated ***");
                        }
                    break;
                    
                case 31: //SUBTRACT
                        accumulator -= memory[operand];
                        //Exception handling of accumulator overflow
                        if (accumulator < -99999 || accumulator > 99999) 
                        {
                            error = true;
                            throw new IllegalArgumentException(
                            "*** Value greater than range (-99999 - +99999) ***\n"
                            + "*** TimpletrÖn execution abnormally terminated ***");
                        }
                    break;
                    
                case 32: //DIVIDE
                    try
                    {
                        accumulator /= memory[operand];
                        //Exception handling of accumulator overflow
                        if (accumulator < -99999 || accumulator > 99999) 
                        {
                            error = true;
                            throw new IllegalArgumentException(
                            "*** Value greater than range (-99999 - +99999) ***\n"
                            + "*** TimpletrÖn execution abnormally terminated ***");
                        }
                    }
                    catch(ArithmeticException e)    //Exception handling for attempts to divide by zero
                    {
                        System.out.println(e);
                        System.out.println("*** Attempt to divide by zero ***\n"
                                + "*** TimpletrÖn execution abnormally terminated ***");
                        error = true;
                    }
                    break;
                    
                case 33: //MULTIPLY
                        accumulator *= memory[operand];
                        //Exception handling of accumulator overflow
                        if (accumulator < -99999 || accumulator > 99999) 
                        {
                            error = true;
                            throw new IllegalArgumentException(
                            "*** Value greater than range (-99999 - +99999) ***\n"
                            + "*** TimpletrÖn execution abnormally terminated ***");
                        }
                    break;
                    
                case 34: //REMAINDER
                    try
                    {
                        accumulator %= memory[operand];
                        //Exception handling of accumulator overflow
                        if (accumulator < -99999 || accumulator > 99999) 
                        {
                            error = true;
                            throw new IllegalArgumentException(
                            "*** Value greater than range (-99999 - +99999) ***\n"
                            + "*** TimpletrÖn execution abnormally terminated ***");
                        }
                    }
                    catch(ArithmeticException e) //Exception handling for attempts to divide by zero
                    {
                        System.out.println(e);
                        System.out.println("*** Attempt to divide by zero ***\n"
                                + "*** TimpletrÖn execution abnormally terminated ***");
                        error = true;
                    }
                    break;
                    
                case 35: //EXPONENTIAL
                        accumulator = (int) Math.pow(accumulator, memory[operand]);
                        //Exception handling of accumulator overflow
                        if (accumulator < -99999 || accumulator > 99999) 
                        {
                            error = true;
                            throw new IllegalArgumentException(
                            "*** Value greater than range (-99999 - +99999) ***\n"
                            + "*** TimpletrÖn execution abnormally terminated ***");
                        }
                    break;
                    
                    
                case 40: //BRANCH
                    instructionCounter = operand;
                    break;
                    
                case 41: //BRANCHNEG
                    if (accumulator < 0)
                        instructionCounter = operand;
                    break;
                    
                case 42: //BRANCHZERO
                    if (accumulator == 0)
                        instructionCounter = operand;
                    break;
                    
                case 43: //HALT
                    System.out.println("*** TimpletrÖn execution terminated ***");
                    error = true;
                    break;
                    
                default:
                    //Exception handling for attempts to execute invalid operation codes
                    error = true;
                    throw new IllegalArgumentException(
                    "\n*** Attempt to execute invalid operation code ***\n"
                    + "*** TimpletrÖn execution abnormally terminated ***\n");
            }
            
            if (!error)
                break;
        }
        
        System.out.println();
        dump(accumulator, instructionCounter, instructionRegister, operationCode, operand, memory);
    }
    
    public static void dump(float accumulator, int instructionCounter, float instructionRegister, int operationCode, int operand, float[] memory)
    {
        int count = 0;
        System.out.printf("REGISTERS:\naccumulator:%15.2f\ninstructionCounter:%8d\n"
                + "instructionRegister:%7.2f\noperationCode:%13d\noperand:%19d\n\nMEMORY:\n", accumulator, instructionCounter,
                instructionRegister, operationCode, operand);
        
        while (count < 10)
        {
            if (count == 0)
                System.out.printf("%9d", count);
            else
                System.out.printf("%7d", count);
            count++;
        }
        System.out.println();
        
        count = 0;
        System.out.print(count + "  ");
        for (int counter = 0; counter < memory.length; counter++)
        {
            System.out.printf("+%05d ", memory[counter]);
            
            if (counter % 10 == 9 )
            {
                count += 10;
                System.out.println();
                System.out.print(count + " ");
            }
        }
    }
}
