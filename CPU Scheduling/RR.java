
/**
 * CPU Scheduling using Round robin
 *
 * @author Emeka Okonkwo
 * @version 11.26.2018
 */
import java.util.Random;
public class RR {
    private Random rand = new Random();
    
 public void RR()
 {
       int q = 4;//Time quantum
       int n = 10;//Process ID
       int proc[][] = new int[n + 1][4];
       System.out.println("\nCPU Scheduling using the Round Robin Algorithm\n");
       System.out.println("Process ID\t Burst");
       for(int i = 1; i <= n; i++)
       {
      proc[i][1] = rand.nextInt(10)+1;//Number of Burst
      System.out.printf("%d\t%10dms\n",i,proc[i][1]);
     }
       System.out.println();
     
       //Calculation of Total Time and Initialization of Time Chart array
     int total_time = 0;
     for(int i = 1; i <= n; i++)
     {
      total_time += proc[i][1];
     }
     int time_chart[] = new int[total_time];
     
     int sel_proc = 1;
     int current_q = 0;
     for(int i = 0; i < total_time; i++)
     {
      //Assign selected process to current time in the Chart
      time_chart[i] = sel_proc;
      
      //Decrement Remaining Time of selected process by 1 since it has been assigned the CPU for 1 unit of time
      proc[sel_proc][1]--;
      
      //WT and TT Calculation
      for(int j = 1; j <= n; j++)
      {
       if(proc[j][1] != 0)
       {
        proc[j][3]++;//If process has not completed execution its TT is incremented by 1
        if(j != sel_proc)//If the process has not been currently assigned the CPU its WT is incremented by 1
         proc[j][2]++;
       }
       else if(j == sel_proc)//This is a special case in which the process has been assigned CPU and has completed its execution
        proc[j][3]++;
      }
      
      //Printing the Time Chart
      if(i != 0)
      {
       if(sel_proc != time_chart[i - 1])
        //If the CPU has been assigned to a different Process we need to print the current value of time and the name of 
        //the new Process
       {
        System.out.print("--" + i + "--P" + sel_proc);
       }
      }
      else//If the current time is 0 i.e the printing has just started we need to print the name of the First selected Process
       System.out.print(i + "--P" + sel_proc);
      if(i == total_time - 1)//All the process names have been printed now we have to print the time at which execution ends
       System.out.print("--" + (i + 1));
      
      //Updating value of sel_proc for next iteration
      current_q++;
      if(current_q == q || proc[sel_proc][1] == 0)//If Time slice has expired or the current process has completed execution
      {
       current_q = 0;
       //This will select the next valid value for sel_proc
       for(int j = 1; j <= n; j++)
       {
        sel_proc++;
        if(sel_proc == (n + 1))
            sel_proc = 1;
        if(proc[sel_proc][1] != 0)
         break;
       }
      }
     }
     System.out.println();
     System.out.println();
     
     //Printing the Waiting time and Turnaround time for each Process
     System.out.println("Process ID\t WT  \t    TT  ");
     for(int i = 1; i <= n; i++)
     {
      System.out.printf("%d\t%10dms\t%5dms",i,proc[i][2],proc[i][3]);
      System.out.println();
     }
     
     System.out.println();
     
     //Printing the average Waiting time and Turnaround time
     float WT = 0,TT = 0;
     for(int i = 1; i <= n; i++)
     {
      WT += proc[i][2];
      TT += proc[i][3];
     }
     WT /= n;
     TT /= n;
     System.out.println("The AWT is: " + WT + "ms");
     System.out.println("The ATT is: " + TT + "ms\n");
 }
    
}