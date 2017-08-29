package algo;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class APSP {
	BufferedReader br;
	FileReader fr;
	FileWriter fw;
	BufferedWriter bw;
	int nVert;
	int nEdge;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		APSP obj=new APSP();
		
		
		
		int test[][]={ {0,   5,  9999, 10},
                {9999, 0,   3, 9999},
                {9999, 9999, 0,   1},
                {9999, 9999, 9999, 0}
              };
//		System.out.println(obj.nVert);
//		obj.nVert=test.length;
		int adjMat1[][]=obj.readFile("input_random_28_128");
		String apsp1[][]=obj.algoFloydWarshall3(adjMat1);
		int adjMat2[][]=obj.readFile("input_random_29_256");
		String apsp2[][]=obj.algoFloydWarshall3(adjMat2);
		int adjMat3[][]=obj.readFile("input_random_38_1024");
		String apsp3[][]=obj.algoFloydWarshall3(adjMat3);
		boolean flag1=false,flag2=false,flag3=false;
		for(int i=0;i<apsp1.length;i++) {
			for(int j=0;j<apsp1.length;j++) {
				if(i==j&&Long.parseLong(apsp1[i][j])<0) {
//					System.out.print(apsp[i][j]+" ");
//					System.out.println(apsp[i][j]+" ");
//					System.out.println("neg");
					flag1=true;
				}
				if(i==j&&Long.parseLong(apsp2[i][j])<0) {
//					System.out.print(apsp[i][j]+" ");
//					System.out.println(apsp[i][j]+" ");
//					System.out.println("neg");
					flag2=true;
				}				
				if(i==j&&Long.parseLong(apsp3[i][j])<0) {
//					System.out.print(apsp[i][j]+" ");
//					System.out.println(apsp[i][j]+" ");
//					System.out.println("neg");
					flag3=true;
				}				
				
			}
//			if(i==1)
//				break;
//			System.out.println();			
		}		
		if(flag1==true)
			System.out.println("1 neg cycle");
		else
			System.out.println("1 no neg cycle");
		if(flag2==true)
			System.out.println("2 neg cycle");
		else
			System.out.println("2 no neg cycle");
		if(flag3==true)
			System.out.println("3 neg cycle");
		else
			System.out.println("3 no neg cycle");

		
//		System.out.println(apsp.length);
		long min=Long.MAX_VALUE;
//		for(int i=0;i<apsp.length;i++) {
//			for(int j=0;j<apsp.length;j++) {
//				if(apsp[i][j]!="INF") {
//					if(min>Long.parseLong(apsp[i][j])) {
//						
//						min=Long.parseLong(apsp[i][j]);
//					}
//					
//				}
//			}
//			
//		}
		System.out.println(min);
		obj.fileWriter("g1_out", apsp1);
	}
	public void fileWriter(String filename, String arr[][]) {
		try {
			fw = new FileWriter("C:\\Users\\tsogtbayarn\\Documents\\Workspace\\java\\" + filename + ".txt");
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr.length;j++) {
				try {
					bw.write(arr[i][j] + "	");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				bw.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Writer writer=null;
//		try {
//			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\tsogtbayarn\\Documents\\Workspace\\java\\"+filename+".txt"), "utf-8"));
//			writer.write("something");
//		} catch (UnsupportedEncodingException | FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				writer.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	   	
	              
			
						
		
		

	}
	public long[][] algoFloydWarshall4(int arr[][]){
		long A[][]=new long[nVert][nVert];
		
		System.out.println("A created");
		for(int i=0;i<nVert;i++) {
			for(int j=0;j<nVert;j++) {
				if(i==j) {
					A[i][j]=0;	
				}
				else if(arr[i][j]!=0) {
					A[i][j]=arr[i][j];
				}
				else {
					A[i][j]=99999;
				}	
			}
		}
		
		System.out.println("A initialized");
		
		for(int k=0;k<nVert;k++) {
			for(int i=0;i<nVert;i++) {				
				for(int j=0;j<nVert;j++) {
					if(A[i][j]>A[i][k]+A[k][j]) {
						A[i][j]=A[i][k]+A[k][j];
//						System.out.println(A[i][j]);
					}
				}
			}			
		}			
		return A;
	}	
	
	public String[][] algoFloydWarshall3(int arr[][]){
		String A[][]=new String[nVert][nVert];
		
		System.out.println("A created");
		for(int i=0;i<nVert;i++) {
			for(int j=0;j<nVert;j++) {
				if(i==j) {
					A[i][j]=Long.toString(0);	
				}
				else if(arr[i][j]!=0) {
					A[i][j]=Long.toString(arr[i][j]);
				}
				else {
					A[i][j]="INF";
				}	
			}
		}
		System.out.println("A initialized");
		for(int k=0;k<nVert;k++) {
			for(int i=0;i<nVert;i++) {				
				for(int j=0;j<nVert;j++) {
					if(A[i][j]!="INF"&&A[i][k]!="INF"&&A[k][j]!="INF") {
						if(Long.parseLong(A[i][j])>Long.parseLong(A[i][k])+Long.parseLong(A[k][j])) {
							A[i][j]=Long.toString(Long.parseLong(A[i][k])+Long.parseLong(A[k][j]));			
						}						
					}
					else if(A[i][j]=="INF"&&A[i][k]!="INF"&&A[k][j]!="INF") {
						A[i][j]=Long.toString(Long.parseLong(A[i][k])+Long.parseLong(A[k][j]));
					}
				}
			}		
		}			
		return A;
	}
	
	public void algoFloydWarshall2(int arr[][]){
//		int A[][][]=new int[nVert][nVert][nVert];
//		ArrayList<Integer[][]> B=new ArrayList<Integer[][]>();
		ArrayList<ArrayList<int[]>> A= new ArrayList<ArrayList<int[]>>();
		
		System.out.println("A created");
		for(int i=0;i<nVert;i++) {
			ArrayList<int[]> tmpList=new ArrayList<int[]>();

			for(int j=0;j<nVert;j++) {
				
				int[] tmpIntList=new int[nVert];
				
				if(i==j) {
					tmpIntList[0]=0;
					tmpList.add(tmpIntList);	
				}
				else if(arr[i][j]!=0) {
					tmpIntList[0]=arr[i][j];
					tmpList.add(tmpIntList);
					
				}
				else {
					tmpIntList[0]=Integer.MAX_VALUE;
					tmpList.add(tmpIntList);
				}
				
//				tmpList.add(tmpIntList);
//				tmpIntList=tmpList.get(j);				
			}
			A.add(tmpList);
//			tmpList=A.get(i);
		}

		System.out.println(A.size());
		System.out.println(A.get(10).get(123).length);
		System.out.println("A initialized");
		
		ArrayList<ArrayList<Integer>> tmpList=new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> tmpIntList=new ArrayList<Integer>();
		int i,j,k;
		
//		for(k=1;k<nVert;k++) {
//			for(i=1;i<nVert;i++) {				
//				tmpList=A.get(i);
//				for(j=1;j<nVert;j++) {
//					
//					tmpIntList=tmpList.get(j);
//					if(tmpIntList.get(k-1)<tmpList.get(k).get(k-1)+tmpList.get(j).get(k-1)) {
//						tmpIntList.add(tmpIntList.get(k-1));						
//					}
//					else {
//						tmpIntList.add(tmpList.get(k).get(k-1)+tmpList.get(j).get(k-1));						
//					}
//					tmpList.set(j,tmpIntList);
//						
//				}
//				A.set(i,tmpList);				
//			}
//			
//		}
			
//		for(k=1;k<nVert;k++) {
//			for(i=1;i<nVert;i++) {				
////				tmpList=A.get(i);
//				for(j=1;j<nVert;j++) {
//					
////					tmpIntList=tmpList.get(j);
//					if(A.get(i).get(j).get(k-1)<A.get(i).get(k).get(k-1)+A.get(i).get(j).get(k-1)) {
//						A.get(i).get(j).add(A.get(i).get(j).get(k-1));						
//					}
//					else {
//						A.get(i).get(j).add(A.get(i).get(k).get(k-1)+A.get(i).get(j).get(k-1));						
//					}
////					A.get(i).set(j,tmpIntList);
//						
//				}
////				A.set(i,tmpList);				
//			}
//			
//		}
		
	}
	
	public void algoFloydWarshall(int arr[][]){
		int A[][][]=new int[nVert][nVert][nVert];
		
		System.out.println("A created");
		for(int i=0;i<nVert;i++) {
			for(int j=0;j<nVert;j++) {
				if(i==j) {
					A[i][j][0]=0;	
				}
				else if(arr[i][j]!=0) {
					A[i][j][0]=arr[i][j];
				}
				else {
					A[i][j][0]=Integer.MAX_VALUE;
				}
				
			}
		}
		System.out.println("A initialized");
		for(int k=1;k<nVert;k++) {
			for(int i=1;i<nVert;i++) {				
				for(int j=1;j<nVert;j++) {
					if(A[i][j][k-1]>A[i][k][k-1]+A[k][i][k-1]) {
						A[i][j][k]=A[i][j][k-1];
					}
					else {
						A[i][j][k]=A[i][k][k-1]+A[k][i][k-1];
					}
				}
			}
			
		}
			
		
	}
	public int[][] readFile(String filename) {
		try {
			fr = new FileReader("C:\\Users\\tsogtbayarn\\Documents\\Workspace\\java\\" + filename + ".txt");
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {		
			String i;
			
			List<String> fileList = new ArrayList<String>();
			
			while(true) {				
				i = br.readLine();
				if (i == null)
					break;
			
				fileList.add(i);
				

			}
			br.close();
			fr.close();
			nVert=Integer.parseInt(fileList.get(0).split(" ")[0]);
			nEdge=Integer.parseInt(fileList.get(0).split(" ")[1]);
			
			int arr[][]=new int[nVert][nVert];
			
			for(int j=1;j<fileList.size();j++) {
				arr[Integer.parseInt(fileList.get(j).split(" ")[0])-1][Integer.parseInt(fileList.get(j).split(" ")[1])-1]=
						Integer.parseInt(fileList.get(j).split(" ")[2]);
				
			}
			
			
			return arr;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}		
}
