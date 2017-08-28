package algo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class APSP {
	BufferedReader br;
	FileReader fr;
	int nVert;
	int nEdge;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		APSP obj=new APSP();
		int adjMat[][]=obj.readFile("g1");
//		System.out.println(obj.nVert);
		obj.algoFloydWarshall2(adjMat);
	}
	public void algoJohnson() {
		
	}
	public void algoFloydWarshall2(int arr[][]){
//		int A[][][]=new int[nVert][nVert][nVert];
//		ArrayList<Integer[][]> B=new ArrayList<Integer[][]>();
		ArrayList<ArrayList<ArrayList<Integer>>> A= new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		System.out.println("A created");
		for(int i=0;i<nVert;i++) {
			ArrayList<ArrayList<Integer>> tmpList=new ArrayList<ArrayList<Integer>>();

			for(int j=0;j<nVert;j++) {
				
				ArrayList<Integer> tmpIntList=new ArrayList<Integer>();
				
				if(i==j) {
					tmpIntList.add(Integer.valueOf(0));	
				}
				else if(arr[i][j]!=0) {
					tmpIntList.add(Integer.valueOf(arr[i][j]));
				}
				else {
					tmpIntList.add(Integer.valueOf(Integer.MAX_VALUE));
				}
				
				tmpList.add(tmpIntList);
//				tmpIntList=tmpList.get(j);				
			}
			A.add(tmpList);
//			tmpList=A.get(i);
		}
		System.out.println(A.size());
		System.out.println(A.get(10).size());
		System.out.println(A.get(10).get(101).get(0));
//		System.out.println(A.get(10).get(241).get(1));
		
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
			
		for(k=1;k<nVert;k++) {
			for(i=1;i<nVert;i++) {				
//				tmpList=A.get(i);
				for(j=1;j<nVert;j++) {
					
//					tmpIntList=tmpList.get(j);
					if(A.get(i).get(j).get(k-1)<A.get(i).get(k).get(k-1)+A.get(i).get(j).get(k-1)) {
						A.get(i).get(j).add(A.get(i).get(j).get(k-1));						
					}
					else {
						A.get(i).get(j).add(A.get(i).get(k).get(k-1)+A.get(i).get(j).get(k-1));						
					}
//					A.get(i).set(j,tmpIntList);
						
				}
//				A.set(i,tmpList);				
			}
			
		}
		
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
				arr[Integer.parseInt(fileList.get(j).split(" ")[1])-1][Integer.parseInt(fileList.get(j).split(" ")[0])-1]=
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
