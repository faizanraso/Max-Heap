public class MaxHeap {
    private int maxSize;  //this variable stores the max size of the array/heap.
    private int currentSize; //this variable stores the current number of integers in the heap. This number is initiazlized as 0
    private Integer[] myArray;  //this array stores all the values of the heap
    
    
    public MaxHeap(Integer[] someArray){ //the following method will create a maxheap using values from the array input
    	myArray = new Integer[someArray.length];
    	this.maxSize = someArray.length;
    	
        for(int counter = 0; counter < maxSize; counter++){
            this.insert(someArray[counter]);
        }
        
    }
    
    public MaxHeap(int max){
    	this.currentSize = 0; //sets current size as 0
        this.maxSize = max; //sets max size of array/heap to input value
        this.myArray = new Integer[max]; //creates array of max size
    }

    
    public static void heapsort(Integer[] arrayToSort){
        MaxHeap temp = new MaxHeap(arrayToSort);
        int counter = 0;
        while(counter < temp.currentSize){
            arrayToSort[counter] = temp.deleteMax();
            counter++;
        }
    }
    
    public String toString(){
        String soln = new String(""); //begin with an empty string which will have values appended to it
        
        if(myArray[0] != 0) { //if the array is not null
            soln = Integer.toString(myArray[0]); //add the first value of the array
            
            for(int counter = 0; counter < this.currentSize - 1 ; counter++){ //for loop iterates through various indexes of the array
            	soln += ", ";
                soln += Integer.toString(myArray[counter + 1]); //correspondingly adds them to the soln string
                }
            
            }
        else {
        	soln = " "; //no value found in the heap thus returns an mpty string
        }
        return soln; //returns the final soln
    }
    
    
    public void insert(int n){
        int insertLoc;//this is the location where the new number should be inserted
        int parentValue; //temporary integer which stores the value at the parent node 
        int pLoc; //this integer representes the location of the parent node
        
        // the following code recognizes if the array is already full and requires a larger array
        if(this.maxSize <= this.currentSize ){
        	
        	int newSize = this.maxSize + this.maxSize; //sets new size to be twice the original size
            Integer[] NewHeap = new Integer[newSize];  //creates new heap of new sizee
            
            for(int i = 0; i < this.maxSize; i++){
                NewHeap[i] = myArray[i];  //adds everything from the last heap to this heap
            }
            this.myArray = NewHeap; //sets myArray as new heap
            this.maxSize = NewHeap.length; //sets new size of heap 
        }

        this.myArray[this.currentSize] = n; //adds the new value to the array
        this.currentSize++; //adds one to the current size of the array

        insertLoc = this.currentSize - 1; //this is the location where the integer was located
        pLoc = ParentNode(insertLoc);//locates the parent node of the insereted values location
       
        while(myArray[pLoc].intValue() < n){ //now the heap will rearrange into the correct order
        	
            parentValue = myArray[pLoc]; //obtains the inserted values parents value
            myArray[pLoc] = n; //sets the parent = to n
            myArray[insertLoc] = parentValue; // sets the insereted index = to its former parent
            insertLoc = pLoc; //Changes what insertLoc is now and continues to compare
            pLoc = ParentNode(insertLoc);//locates the parent node of the insereted values location
        }
    }

    
    public int deleteMax(){
        int Soln = myArray[0]; //first obtains the max value in the array this will be returned at the end
        myArray[0] = myArray[currentSize - 1]; //takes the last inserted number and brings it to the top
        currentSize -= 1; // decreseases the size of the heap by 1 as it is about to remove one numner
        
        //these three integers are used and altered in the for loop
        int counter = 0;
        int reference = 0;
        int value = myArray[counter];
        
        
        while((counter*2)+1 < currentSize) {
        	reference = biggerVal(counter); //sets reference equal to the larger child of that specific node
        	
        	//now performs a comparison of the values and decides if they need to be swapped or not
        	if(value<myArray[reference]) {
        		myArray[counter] = myArray[reference];
        	}
        	else { //if not it comes to this case which means that it is already in heep format
        		break;
        	}
        	counter = reference; // this is to change the index position
        }
        
        myArray[counter] = value;
        return Soln;
        
    }
    
    
    //the following method can be used to obtain the parent node of a specific index in the heap
	public int ParentNode(int n){
	    int temp;
	    if(n % 2 ==0) {
	    	temp = (n/2) - 1;
	    	if(temp < 0) {
	    		temp = 0;
	    	}
	    }
	    else {
	    	temp = n/2;
	    }
		return temp;
	}
	
	//obtains left node of a parent
	public int LeftNode(int n){
	    int temp = (n*2)+1;
		return temp;
	}
	
	//obtains the right node of a parent
	public int RightNode(int n){
		int temp = (n*2)+2;
		return temp;
	}
	
	// returns the number of values in the heap currntly
	public int getSize() {
		return currentSize;
	}
	
	//returns the maximum capacity of the heap
	public int getCapacity() {
		return maxSize;
	}
	
	
	//the following metod determines what the larger child of a node is
	public int biggerVal(int n) {
		int right = this.RightNode(n);
		int left = this.LeftNode(n);
		
		if(right > left) {
			return right;
		}
		else {
			return left;
		}
	}
}
