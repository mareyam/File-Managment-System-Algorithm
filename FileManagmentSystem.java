/*
This program was created by Maryam Naveed 
Roll Number: FA19 BSE 069

*/



package JavaApplication129;
import java.util.Scanner;

public class FileManagmentSystem
{
    public static FileInfo FI;
    public static FileData FD;
    public static FileData[] FDarray = new FileData[1000];
    public static FileInfo[] FIarray = new FileInfo[1000];
    public static char[] CharFileName = null; 
    public static char[] CharData = null; 
    public static char[] CharExt = null; 
    public static String data = null;
    public static String file = null;
    public static String ext = null;
    
    public static Scanner input = new Scanner(System.in);
    public static int indexCounter = 0;
    public static int dataCounter = 0;
    public static int startCounter = 0;
    public static int fileCounter = 0;
    
    public static int checkCounterForCreateFIle= 0;
    
    
    
    public static void main(String args[])
    {
       settingFileIndex();        //setting contents to -1    
        int choiceForSwitch = 0;
        while (true) {
            System.out.print("\n\t\t 1: Create New File");
            System.out.print("\n\t\t 2: Delete File ");
            System.out.print("\n\t\t 3: View File ");
            System.out.print("\n\t\t 4: View All Files");
            System.out.print("\n\t\t 0: Exit. ");
            System.out.print("\n\t\t Enter Your Choice:    ");
            
            choiceForSwitch = input.nextInt();
            if (choiceForSwitch == 0) 
            {
                break;
            }


            switch (choiceForSwitch) 
         {
                case 1:
                        if(checksForCreateFile())             //if space available
                        {
                            enterContentsInFile();            //enter details in string
                            IntoToCharArray(file,ext);        //convert into character array
                            createFile(CharFileName,CharExt,data);      //create file
                         }
                            else
                                  System.out.println("No space available:"); 
                    break;


                case 2:
                      if(checksForDeleteFile(CharFileName, CharExt))  //if file exists
                        {
                            deleteFileContents();   //delete contents and replace with -1
                        }
                             else
                                  System.out.println(CharFileName+ " with Extension "+ CharExt+ " is not available");
                    break;


                case 3:
                       if(checkForViewingFiles())  //if files are available
                        {
                           viewOneFile(CharFileName, CharExt);  //enter details
                        }
                        else
                        {
                             System.out.println("File Not Found");
                        }
                   break;


                case 4:
                       if(checkForViewingFiles()) //if files are available
                        {
                            ViewingAllFIles();  //print all details
                        }
                        else
                        {
                          System.out.println("No file available");
                        }
                    break;


                case 5:
                        {
                         System.out.println("Exiting");
                        }
                        break;
            }
        }
}
    
    
     public static void settingFileIndex()
    {
        for(int i=0; i<10;i++)
        {
            FDarray[i] = new FileData();  
            FDarray[i].nextIndex = -1;
        }
         FDarray[9].nextIndex = -3;  //indiciting end of space
        
        for(int i=0; i<10;i++)
        {
             FIarray[i] = new FileInfo();
             FIarray[i].start = -1;
        }
         FIarray[9].start = -3;  //indiciting end of space
    }
     
     /*
        checks for differnet methods
     
    */
     
     
    public static boolean checksForCreateFile()
    {
        int checkCounterForCreateFIle = 0;
        while(FDarray[checkCounterForCreateFIle].nextIndex != -1)    
        {
             checkCounterForCreateFIle++;
             if(FDarray[checkCounterForCreateFIle].nextIndex == -3)  //end of space
                return false;
        }
        return true;
    }
    
    
    public static boolean checksForDeleteFile(char[] fileName, char[] fileExt)
   {
       for(int i=0; i<FIarray.length;i++)
       {
           if(FIarray[i].fileName == fileName && FIarray[i].fileExt == fileExt) 
           {
               return true;
           }
       }
        System.out.println("File Not Found\n");
         return false;
   }
    
    
     public static boolean checkForViewingFiles()
    {
        for(int i=0; i<indexCounter; i++)
        {
            if(FIarray[i].fileName == null || FIarray[i].fileExt == null || FDarray[i].data == -3) //if empty
                return false;
        }
        return true;
    }
     
     
    
       public static boolean viewOneFile(char[] fileName, char[] fileExt)
    {
         for(int i=0; i<indexCounter; i++)    //less than the data entered
         {
            if(FIarray[i].fileName == fileName && FIarray[i].fileExt == fileExt) //filename and extension matched
           {
               for(int j=i; j<FDarray.length; j++)  //if matched^ start printing data
               {
                  if(FDarray[i].nextIndex == -2)        //if end of file(-2) reached
                   {
                       System.out.println(FDarray[i]);  //print last fileContent
                       System.out.println("File Data Finished..");
                      return true;
                   }
                   System.out.println(FDarray[i]);    //print until end of file
               }
             return true;
           }
         }
         return false;
    }
   
    public static void enterContentsInFile()
    {
        // data will be converted into char array later
           System.out.print("\n\t\tEnter File Name\n\t\t");
           file = input.nextLine();
           System.out.print("\n\t\tEnter File Extension\n\t\t");
           ext = input.nextLine();
           System.out.print("\n\t\tEnter File Data\n\t\t");
           data = input.nextLine();
    }
    
    public static void IntoToCharArray(String file, String ext)
    {
        //converting into character arrays
        CharFileName = file.toCharArray();
        CharExt = ext.toCharArray();
    }     
         
    public static void createFile(char[] fileName, char[] ext, String data)
    { 
        for(int i=0; i<data.length(); i++)
        {
            char charData = data.charAt(i);
            FDarray[dataCounter++] =  new FileData(charData); 
            FDarray[indexCounter].nextIndex = ++indexCounter;
        }
            FDarray[indexCounter-1].nextIndex = -2;
            FIarray[fileCounter++] = new FileInfo(fileName, ext);
         /*
        Line 218 will take the current index counter and subtract the current data's length and will add 1 into it
        so that the start of file is found
        */
            FIarray[startCounter++].start = indexCounter-data.length()+1;
        
    }     
    

    public static void enterFileDetails()
   {
        System.out.print("\n\t\tEnter File Name");
                       file = input.nextLine();
                       System.out.print("\n\t\tEnter File Extension");
                       ext = input.nextLine();
                       CharFileName = file.toCharArray();
                       CharExt = ext.toCharArray();
   }
    
   public static boolean deleteFileContents()
   {
        int counterForDelete = 0;
        for(int i=0; i<FDarray.length; i++)
        {
            while(FDarray[counterForDelete].nextIndex != -2 && (counterForDelete <= FIarray.length))
               {
                        if(FDarray[counterForDelete].nextIndex == -3)
                        {
                            System.out.println("File Has Ended..");
                             return true;
                        }
                      
                   FDarray[i].nextIndex = -1;
                   counterForDelete++;
               }
           }
             System.out.println("File Deleted Successfully..\n");
             return true;  
   }
   
   
    public static void ViewingAllFIles()
    {
        for(int i=0; i<dataCounter; i++)
        {
            System.out.println("File Name is: %s"+FIarray[i].fileName
                                  +"File Extension is: %s"+FIarray[i].fileExt
                                  +"File Data is: %s"+FDarray[i].data);
        }
    }
}
class FileData
{
    char data = 0;
    int nextIndex = 0;
    
    public FileData(char data)
    {
        this.data = data;
    }
    public FileData()
    {
       
    }
}
class FileInfo 
{
    char[] fileName;
    char[] fileExt;
    int start;
    public FileInfo(char[] fileName, char[] fileExt)
    {
        this.fileName = fileName; 
        this.fileExt =  fileExt;
    }
      public FileInfo()
    {
        fileName = new char[10];
        fileExt =  new char[3];
    }
}