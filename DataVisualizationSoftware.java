/*******************************
 * 
 * Project: Data visualization software
 * Language: Java
 * Library: StdDraw.java
 * Date: September 2021
 * 
 * Dev: Jayed Rafi
 *
*******************************/

public class DataVisualizationSoftware
{
    /**
     * use Interface on main() to modify your data
     * max number of data is 3 (max 3 arrays can be used)
     * if don'y need any array, change the values to 0
     * it has built-in compresser! use any value you want!
     * you can also save the chart image!!
     */
    public static void main(String[] args)
    {
        //--------------------------------------------------------
        //-------------------- User Interface --------------------
        //--------------------------------------------------------
        
        
        //cange the values or size as you need
        double[] column_one = {0.94,0.95,0.97,0.99,1.04,1.10,1.16,1.21,1.24,1.23};
        double[] column_two = {0.16,0.15,0.14,0.13,0.11,0.10,0.08,0.06,0.03,0.01};
        double[] column_three = {1.08,1.07,1.08,1.15,1.30,1.50,1.72,1.90,1.99,1.96};
        
        //make true which ever visualization you want
        boolean show_column_chart = false;
        boolean show_line_chart = true;
        
        //make true if you want to save the image as .png file
        boolean save_the_chart = true;
        
        //name datas
        String data_name_one = "Canada population growth rate (2000 - 2009)";
        String data_name_two = "Japan population growth rate (2000 - 2009)";
        String data_name_three = "Australia population growth rate (2000 - 2009)";
        
        
        //--------------------------------------------------------
        //----------------- End of User Interface ----------------
        //--------------------------------------------------------
        
        tools compress = new tools();
        double maxNum = compress.maximumValue(column_one,column_two,column_three);
        if(show_column_chart)
        new ColumnChart(compress.optimizer(column_one,maxNum),compress.optimizer(column_two,maxNum),compress.optimizer(column_three,maxNum),save_the_chart,data_name_one,data_name_two,data_name_three);
        else if(show_line_chart)
        new LineChart(compress.optimizer(column_one,maxNum),compress.optimizer(column_two,maxNum),compress.optimizer(column_three,maxNum),save_the_chart,data_name_one,data_name_two,data_name_three);
    }
}//end class DataVisualizationSoftware

class tools
{   
    public tools()
    {}//constructor
    
    //finds the maximum value in all three arrays
    public double maximumValue(double[] one, double[] two, double[] three)
    {
        double[] maximums = new double [3];
        maximums[0] = findMaximum(one);
        maximums[1] = findMaximum(two);
        maximums[2] = findMaximum(three);
        return findMaximum(maximums);
    }
    
    //helper
    private double findMaximum(double[] values)
    {
        double max = values[0];
        for(int i=0; i<values.length; i++)
        {
         if(values[i]>max)
         {
             max = values[i];
            }
        }
        return max;
    }
    
    /**
     * compress the numbers and convert it to a a value from 0 to 0.6
     * formula = (0.6x number)/maximum value in all three arrays
     */
    public double[] optimizer(double[] dataValues, double max)
    {
        double[] modifiedValues = new double [dataValues.length];
        
        for(int i=0; i<dataValues.length; i++)
        {
            modifiedValues[i] = (0.6*dataValues[i])/max;
        }
        
        return modifiedValues;
    }
    
}//end class tools

class ColumnChart
{
    double[] column_one,column_two,column_three;
    String dataOne, dataTwo, dataThree;
    boolean saveFile;
    
    public ColumnChart(double[] column_one,double[] column_two, double[] column_three, boolean saveFile, String dataOne, String dataTwo, String dataThree)
    {
        this.column_one = column_one;
        this.column_two = column_two;
        this.column_three = column_three;
        this.saveFile = saveFile;
        this.dataOne = dataOne;
        this.dataTwo = dataTwo;
        this.dataThree = dataThree;
        visualize();
    }//constructor
    
    public void visualize()
    {
        //canvas dimensions
        double halfCanvasWidth = 0.4;
        double halfCanvasHeight = 0.3;
        int numColumn = column_one.length; //number of column per data type
        int numData = 4;//number of data types
        double halfWidth = ((halfCanvasWidth*2)/(numColumn*numData))/2;//half width of columns
        double x1,x2,x3,x4,x5,x6,y1,y2,y3,y4,y5,y6; //coordinates
        
        
        StdDraw.setCanvasSize(800,800); //display size
        
        //Texts and colour adjustment
        StdDraw.text(0.5,0.85,"Data visualization Software");
        StdDraw.text(0.5,0.82,"Visualization type: Column chart");
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledSquare(0.1+0.01,0.1+0.03,0.01);
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.filledSquare(0.1+ 0.01,0.1,0.01);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledSquare(0.1+ 0.01,0.1-0.03,0.01);
        StdDraw.text(0.5,0.18,"Data types - X axis",0);
        StdDraw.text(0.08,0.5,"Values - Y axis",90);
        StdDraw.textLeft(0.1+ 3*0.01,0.1+0.029,dataOne);
        StdDraw.textLeft(0.1+ 3*0.01,0.099,dataTwo);
        StdDraw.textLeft(0.1+ 3*0.01,0.1-0.029,dataThree);
        
        //draw columns
        for(int i=1; i<= numColumn; i++)
        {
            //initialize coordinates on each iteration
            x1 = (0.1-3*halfWidth)+(2*numData*halfWidth)*i;
            y1 = column_one[i-1]/2+0.2;
            x2 = x4 = x6 = halfWidth;
            y2 = column_one[i-1]/2;
            x3 = (0.1-halfWidth)+(2*numData*halfWidth)*i;
            y3 = column_two[i-1]/2+0.2;;
            y4 = column_two[i-1]/2;
            x5 = (0.1-5*halfWidth)+(8*halfWidth)*i;;
            y5 = column_three[i-1]/2+0.2;
            y6 = column_three[i-1]/2;
            
            //column one
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(x1,y1,x2,y2);
            
            //column two
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.filledRectangle(x3,y3,x4,y4);
            
            //column three
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(x5,y5,x6,y6);
        }
        
        //the border
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(0.5,0.5,halfCanvasWidth,halfCanvasHeight);
        
        //saving file
       if(saveFile)
       StdDraw.save("column_chart.png");
    }
}//end class ColumnChart

class LineChart
{
    double[] column_one, column_two, column_three;
    String dataOne, dataTwo, dataThree;
    boolean saveFile;
    
    public LineChart(double[] column_one,double[] column_two, double[] column_three, boolean saveFile,String dataOne, String dataTwo, String dataThree)
    {
        this.column_one = column_one;
        this.column_two = column_two;
        this.column_three = column_three;
        this.saveFile = saveFile;
        this.dataOne = dataOne;
        this.dataTwo = dataTwo;
        this.dataThree = dataThree;
        visualize();
    }//constructor
    
    public void visualize()
    {
       //canvas dimensions
       double halfCanvasWidth = 0.4;
       double halfCanvasHeight = 0.3;
       int numVal = column_one.length-1; //number of values per data type
       int numData = 4;//number of data types
       final int NUM_GRID = 6;
       double lineLength = (halfCanvasWidth*2)/(numVal);//length of lines
       double x1,x2,y1,y2,y3,y4,y5,y6; //coordinates
       
       StdDraw.setCanvasSize(800,800); //display size
       
       //Texts and colour adjustment
        StdDraw.text(0.5,0.85,"Data visualization Software");
        StdDraw.text(0.5,0.82,"Visualization type: Line Chart");
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledSquare(0.1+0.01,0.1+0.03,0.01);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledSquare(0.1+ 0.01,0.1,0.01);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledSquare(0.1+ 0.01,0.1-0.03,0.01);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.text(0.5,0.18,"Data types - X axis",0);
        StdDraw.text(0.08,0.5,"Values - Y axis",90);
        StdDraw.textLeft(0.1+ 3*0.01,0.1+0.029,dataOne);
        StdDraw.textLeft(0.1+ 3*0.01,0.099,dataTwo);
        StdDraw.textLeft(0.1+ 3*0.01,0.1-0.029,dataThree);
       
       //horizontal grids
       for(int i=1; i<NUM_GRID; i++)
       {
           double lineHeight =0.2+((2*halfCanvasHeight)/NUM_GRID)*i;
           StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
           StdDraw.line(0.1,lineHeight,0.1+(2*halfCanvasWidth),lineHeight);
       }
       
       //draw lines
       for(int i=0; i<numVal; i++)
       {
           //initialize coordinates on each iteration
           x1 = 0.1+(lineLength*i);
           x2 = x1+lineLength;
           y1 = 0.2+column_one[i];
           y2 = 0.2+column_one[i+1];
           y3 = 0.2+column_two[i];
           y4 = 0.2+column_two[i+1];
           y5 = 0.2+column_three[i];
           y6 = 0.2+column_three[i+1];
           
         //red lines
         StdDraw.setPenColor(StdDraw.RED);
         StdDraw.line(x1,y1,x2,y2);
         
         //green lines
         StdDraw.setPenColor(StdDraw.GREEN);
         StdDraw.line(x1,y3,x2,y4);
         
         //blue lines
         StdDraw.setPenColor(StdDraw.BLUE);
         StdDraw.line(x1,y5,x2,y6);
        }
       
       //the border
       StdDraw.setPenColor(StdDraw.BLACK);
       StdDraw.rectangle(0.5,0.5,halfCanvasWidth,halfCanvasHeight);
       
       //saving file
       if(saveFile)
       StdDraw.save("line_chart.png");
    }
}//end class LineChart
