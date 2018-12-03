/**
 * CPU Scheduling using SRTF
 *
 * @author Emeka Okonkwo
 * @version 11.26.2018
 */
import java.io.*;
import java.util.Random;
public class SRTF {
    private Random rand = new Random();
 public void SRTF() throws IOException
 {
      int n;//Process
       n = 10;//Set to 10 Processes
       int process[][] = new int[n + 1][4];
       System.out.println("\nCPU Scheduling using the Shortest Remaining Time First Algorithm\n");
       System.out.println("Process ID\t Arrival Time \t Burst");
       for(int i = 1; i <= n; i++)
       {
      process[i][0] = rand.nextInt(10);//Arrival Time
      process[i][1] = rand.nextInt(20)+1;//Random Set of Burst for each processess between 1 and 20 
      System.out.printf("%d\t%20dms\t%10dms\n",i,process[i][0], process[i][1]);
     }
     System.out.println();
     
       //Calculation of Total Time and Initialization of Gantt Chart array
     int total_time = 0;
     for(int i = 1; i <= n; i++)
     {
      total_time += process[i][1];
     }
     int gantt_chart[] = new int[total_time];
     
     for(int i = 0; i < total_time; i++)
     {
      //Selection of shortest processess which has arrived
      int sel_process = 0;
      int min = 99999;
      for(int j = 1; j <= n; j++)
      {
       if(process[j][0] <= i)//Condition to check if Process has arrived
       {
        if(process[j][1] < min && process[j][1] != 0)
        {
         min = process[j][1];
         sel_process = j;
        }
       }
      }
      
      //Assign selected processess to current time in the Chart
      gantt_chart[i] = sel_process;
      
      //Decrement Remaining Time of selected processess by 1 since it has been assigned the CPU for 1 unit of time
      process[sel_process][1]--;
      
      //WT and TT Calculation
      for(int j = 1; j <= n; j++)
      {
       if(process[j][0] <= i)
       {
        if(process[j][1] != 0)
        {
         process[j][3]++;//If processess has arrived and it has not already completed execution its TT is incremented by 1
            if(j != sel_process)//If the processess has not been currently assigned the CPU and has arrived its WT is incremented by 1
             process[j][2]++;
        }
        else if(j == sel_process)//This is a special case in which the processess has been assigned CPU and has completed its execution
         process[j][3]++;
       }
      }
      
      //Printing the Gantt Chart
      if(i != 0)
      {
       if(sel_process != gantt_chart[i - 1])
        //If the CPU has been assigned to a different Process we need to print the current value of time and the name of 
        //the new Process
       {
        System.out.print("--" + i + "--P" + sel_process);
       }
      }
      else//If the current time is 0 i.e the printing has just started we need to print the name of the First selected Process
       System.out.print(i + "--P" + sel_process);
      if(i == total_time - 1)//All the processess names have been printed now we have to print the time at which execution ends
       System.out.print("--" + (i + 1));
      
     }
     System.out.println();
     System.out.println();
     
     //Printing the Waiting time and Turnaround Time for each Process
     System.out.println("Process ID\t WT  \t    TT  ");
     for(int i = 1; i <= n; i++)
     {
      System.out.printf("%d\t%15dms\t%10dms",i,process[i][2],process[i][3]);
      System.out.println();
     }
     
     System.out.println();
     
     //Printing the average Waiting Time and Turnaround Time
     float AWT = 0,ATT = 0;
     for(int i = 1; i <= n; i++)
     {
      AWT += process[i][2];
      ATT += process[i][3];
     }
     AWT /= n;
     ATT /= n;
     System.out.println("The AWT is: " + AWT + "ms");
     System.out.println("The ATT is: " + ATT + "ms\n");
 }
    
}