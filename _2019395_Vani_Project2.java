

import java.util.*;

public class _2019395_Vani_Project2 
{
	public static void main(String[] args) 
	{
		/*The execution of the program begins with main().
		 * This takes the two integer numbers as input it converts them to binary string 
		 * also convert the negative number to the two’s complement.
		 * It performs the booth’s algorithm on the numbers prints all the partial products at each stage.
		 * In the end it print the result in the binary form on the output scene.
		 */
		Scanner in = new Scanner (System.in);
		System.out.println("Operand 1:");
		int M= in.nextInt();
		System.out.println("Operand 2:");
		int Q= in.nextInt();
		String cm=new String(),cq= new String(),ans="";
		String lt="";
		String mi= Integer.toBinaryString(Math.abs(M));
		String qi= Integer.toBinaryString(Math.abs(Q));
		cm =twocomplement(mi,M);
		cq=twocomplement(qi,Q);
		String[] a=  makelengthlequal(cq,cm);
		String m=a[1];
		String q=a[0];
		String acc=getaccval(m);
		String n= acc+q+"0";
		StringBuilder num= new StringBuilder();
		for(int i=0;i<n.length();i++)
			num.append(n.charAt(i));
		for(int i=0;i<q.length();i++)
		{
			if(M>Math.pow(2, 31)|| M<Math.pow(-2, 31) || Q>Math.pow(2, 31)|| Q<Math.pow(-2, 31))
			{
				System.out.println("ERROR: Number entered is out of range");
			}
			lt=num.substring(num.length()-2,num.length());
			if(lt.contentEquals("11")==true || lt.contentEquals("00")==true )
			{
				shiftright(num);
				
			}
			else if(lt.contentEquals("01")==true)
			{
				addacc(num,m);
				shiftright(num);
				
			}
			else if(lt.contentEquals("10")==true)
			{
				subacc(num,m);
				shiftright(num);
				
			}
			System.out.println("Step "+(i+1)+" status:"+num.toString());
		}
		System.out.println("RESULT :"+num.substring(0, num.length()-1));
			
	}
	private static void subacc(StringBuilder num, String m) 
	{
		/*The function takes the partial product and binary string to be subtracted (m) 
		 * as parameters.It calls helper function to convert m to it's two's complement.
		 * Then the function picks first m's length characters from the partial 
		 * product(str) and add m to it by calling addBinary.The function will return the addition result.
		 * Then the function compare if the size of the result is size of m. If it is not then it means that 
		 * there is a carry which we have to ignore so I update the partial product with the substring of the 
		 * addition result which start from 1.But if the length is equal I update the partial product 
		 * with the addition result.
		 */
		String newm=helper (m);
		String acc= num.substring(0,newm.length());
		String str=addBinary(acc,newm) ;
		String s="";
		if(str.length()!=acc.length())
			s=str.substring(1);
		else
			s=str;
		num.replace(0,newm.length(), s);
		
	}
	private static String helper (String s)
	{
		/*This function converts the binary string of a number to it's two's complement.
		 * This piece of function is referenced from https://www.geeksforgeeks.org/efficient-method-2s-complement-binary-string/
		 */
		
		StringBuffer str =new StringBuffer((s));
		int n = str.length(); 
       
        // Traverse the string to get first '1' from 
        // the last of string 
        int i; 
        for (i = n-1 ; i >= 0 ; i--) 
            if (str.charAt(i) == '1') 
                break; 
       
        // If there exists no '1' concat 1 at the 
        // starting of string 
        if (i == -1) 
            return "1" + str; 
       
        // Continue traversal after the position of 
        // first '1' 
        for (int k = i-1 ; k >= 0; k--) 
        { 
            //Just flip the values 
            if (str.charAt(k) == '1') 
                str.replace(k, k+1, "0"); 
            else
                str.replace(k, k+1, "1"); 
        } 
       
        // return the modified string 
        return str.toString(); 
	}
	private static void addacc(StringBuilder num,String m) 
	{
		/*The function takes the partial product and binary string to be added (m) as parameters.
		 * Then the function picks first m's length characters from the partial product(str) and
		 * add m to it by calling addBinary.The function will return the addition result.
		 * Then the function compare if the size of the result is size of m. If it is not then it means that 
		 * there is a carry which we have to ignore so I update the partial product with the substring of the 
		 * addition result which start from 1.But if the length is equal I update the partial product 
		 * with the addition result.
		 */
		String acc= num.substring(0,m.length());
		String str=addBinary(acc,m) ;
		String s="";
		if(str.length()!=acc.length())
			s=str.substring(1);
		else
			s=str;
		num.replace(0, m.length(), s);
		
		
		
		
	}
	 static String addBinary(String a, String b) 
	    { 
		 	/*The function takes two binary string numbers add the two binary number.
		 	 * return the binary representation of the addition result.
		 	 * This piece of function is referenced from https://www.geeksforgeeks.org/program-to-add-two-binary-strings/
		 	 */
	        // Initialize result 
	        String result = "";  
	          
	        // Initialize digit sum 
	        int s = 0;          
	  
	        // Traverse both strings starting  
	        // from last characters 
	        int i = a.length() - 1, j = b.length() - 1; 
	        while (i >= 0 || j >= 0 || s == 1) 
	        { 
	              
	            // Comput sum of last  
	            // digits and carry 
	            s += ((i >= 0)? a.charAt(i) - '0': 0); 
	            s += ((j >= 0)? b.charAt(j) - '0': 0); 
	  
	            // If current digit sum is  
	            // 1 or 3, add 1 to result 
	            result = (char)(s % 2 + '0') + result; 
	  
	            // Compute carry 
	            s /= 2; 
	  
	            // Move to next digits 
	            i--; j--; 
	        } 
	          
	    return result; 
	    } 
	private static void shiftright(StringBuilder num) 
	{
		/*This function takes in the partial product updated the parial 
		 * product by shifting all the digits by one place 
		 */
		for(int i=num.length()-1;i>=1;i--)
		{
			num.setCharAt(i,num.charAt(i-1));
		}
	
		
	}
	private static String getaccval(String m) 
	{
		/*this function returns the a string of 0's whose length is equal to m's length.
		 * eg m="1001"(length of m=4) so the function returns "0000"(it has length 4)
		 */
		String str="";
		for(int i=0;i<m.length();i++)
		{
			str=str+"0";
		}
		return str;
	}
	public static String twocomplement(String a,int val)
	{
		/*if val is more than zero then a string gets a 0 in the front and is returned as it is with
		 * additional 0 in front of it.But if the val is less than 0 then we put 0 in the front of a 
		 * then calculate the 2's complement of a (which has additional 0 not the original a passed
		 * as parameter) .Hence return the String that contain the 2's complement of a.
		 * This piece of function is referenced from https://www.geeksforgeeks.org/efficient-method-2s-complement-binary-string/
		 */
		if(val>0)
		{
			return ("0"+a);
		}
		else
		{
			StringBuffer str =new StringBuffer(("0"+a));
			int n = str.length(); 
	       
	        // Traverse the string to get first '1' from 
	        // the last of string 
	        int i; 
	        for (i = n-1 ; i >= 0 ; i--) 
	            if (str.charAt(i) == '1') 
	                break; 
	       
	        // If there exists no '1' concat 1 at the 
	        // starting of string 
	        if (i == -1) 
	            return "1" + str; 
	       
	        // Continue traversal after the position of 
	        // first '1' 
	        for (int k = i-1 ; k >= 0; k--) 
	        { 
	            //Just flip the values 
	            if (str.charAt(k) == '1') 
	                str.replace(k, k+1, "0"); 
	            else
	                str.replace(k, k+1, "1"); 
	        } 
	       
	        // return the modified string 
	        return str.toString(); 
		}
	}
	public static String[] makelengthlequal(String q,String m)
	{
		/*This function takes two binary number as inputs. Then it see if their length of both the binary 
		 * numbers are same then it would return booth the numbers as is in the an array where the first
		 * element would be q number and second element will be  m number.But if the numbers are not of the
		 * same size then the smaller number must be add with lsb bit in the front till small one's length
		 * become equal to larger one's length.eg:q="1001" m="100" so m will be changed to "1100"or q="100"
		 * m="01" so now m become "001".Once q and m is ready the it puts in the string array where q is the 
		 * first element and m is the second element. 
		*/
		String mystring="";
		if(q.length()>m.length())
		{
			char s= m.charAt(0);
			String start="";
			for(int i=0;i<q.length()-m.length();i++)
				start=start+s;
			mystring=start+m;
			String[] arr= {q,mystring};
			return arr;
		}
		else if(q.length()<m.length())
		{
			char s= q.charAt(0);
			String start="";
			for(int i=0;i<m.length()-q.length();i++)
				start=start+s;
			mystring=start+q;
			String[] arr= {mystring,m};
			return arr;
		}
		else 
		{
			String[] arr= {q,m};
			return arr;
		}
		
	}
}

