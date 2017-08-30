package algo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APSP2 {
	FileReader fr;
	BufferedReader br;
	int nVert,nEdge;
	ArrayList<ArrayList<Integer>> vertices;
	ArrayList<int[]> edges;
	boolean check=false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		APSP2 obj=new APSP2();
		obj.readFile("g3");
		int res[][]=new int[obj.nVert][obj.nVert];
		boolean cycle=false;
		int min=Integer.MAX_VALUE;
		for(int i=0;i<obj.nVert;i++) {
			int A[][]=obj.bellmanFord(i+1);
			if(obj.check==true)
				break;
			for(int j=0;j<obj.nVert;j++) {
				if(min>A[obj.nVert][j]) {
					min=A[obj.nVert][j];
				}
			}
			
			
/*			if(!Arrays.equals(A[obj.nVert],A[obj.nVert-1])) {
				cycle=true;
				break;
			}
			
			if(cycle==true) {
				System.out.println("neg cycle");
				break;
			}				
*/			
			System.out.println(i);
		}
		
		System.out.println(min);
/*		int A1[][]=obj.bellmanFord(1);
		int A2[][]=obj.bellmanFord(2);
		int A3[][]=obj.bellmanFord(3);
		int A4[][]=obj.bellmanFord(4);
		for(int i=0;i<obj.nVert;i++) {
			System.out.print(A1[obj.nVert][i]+" ");
		}
		System.out.println();
		for(int i=0;i<obj.nVert;i++) {
			System.out.print(A2[obj.nVert][i]+" ");
		}
		System.out.println();
		for(int i=0;i<obj.nVert;i++) {
			System.out.print(A3[obj.nVert][i]+" ");
		}
		System.out.println();
		for(int i=0;i<obj.nVert;i++) {
			System.out.print(A4[obj.nVert][i]+" ");
		}
*/		
		
/*		for(int i=0;i<obj.vertices.size();i++) {
			System.out.print(i+1+": ");
			for(int j:obj.vertices.get(i)) {
				System.out.print(j+2+" ");
			}
			System.out.println();
		}*/
		
	}
	public int[][] bellmanFord(int s) {
		int A[][]=new int[nVert+1][nVert];
		for(int i=0;i<nVert;i++) {
			if(s-1==i) {
				A[0][s-1]=0;
			}
			else
				A[0][i]=Integer.MAX_VALUE;
		}
		
/*		for(int i=1;i<=nVert-1;i++) {
			for(int j=0;j<nEdge;j++) {
				if(A[i-1][edges.get(j)[0]-1]!=99999) {
					if(A[i-1][edges.get(j)[1]-1]>A[i-1][edges.get(j)[0]-1]+edges.get(j)[2]) {
						A[i][edges.get(j)[1]-1]=A[i-1][edges.get(j)[0]-1]+edges.get(j)[2];
					}
					else
						A[i][edges.get(j)[1]-1]=A[i-1][edges.get(j)[1]-1];
				}					
			}			
		}*/
		
		
		
		
//		System.out.println(vertices.get(0).get(1));
		
		for(int i=1;i<=nVert-1;i++) {
			for(int j=0;j<nVert;j++) {
				int min=Integer.MAX_VALUE;
				for(int e:vertices.get(j)) {
					if(j==edges.get(e)[1]-1) {
						if(A[i-1][edges.get(e)[0]-1]!=Integer.MAX_VALUE&&min>A[i-1][edges.get(e)[0]-1]+edges.get(e)[2]) {
							min=A[i-1][edges.get(e)[0]-1]+edges.get(e)[2];
						}
					}					
				}
				if(min>A[i-1][j]) {
					A[i][j]=A[i-1][j];
				}
				else
					A[i][j]=min;
			}
			
		}

		for(int j=0;j<nVert;j++) {
			int min=Integer.MAX_VALUE;
			for(int e:vertices.get(j)) {
				if(j==edges.get(e)[1]-1) {
					if(A[nVert-1][edges.get(e)[0]-1]!=Integer.MAX_VALUE&&min>A[nVert-1][edges.get(e)[0]-1]+edges.get(e)[2]) {
						min=A[nVert-1][edges.get(e)[0]-1]+edges.get(e)[2];
					}
				}					
			}
			if(min>A[nVert-1][j]) {
				A[nVert][j]=A[nVert-1][j];
			}
			else {
				A[nVert][j]=min;
			}
			
			if(A[nVert][j]!=A[nVert-1][j]) {
				check=true;
				System.out.println("neg cycle");
				break;
				
			}
			
		}
		
		
		return A;
	}
	public void readFile(String filename) {
		try {
			fr = new FileReader("/home/stark/Documents/Workspace/java/" + filename + ".txt");
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
			vertices=new ArrayList<ArrayList<Integer>>();
			edges=new ArrayList<int[]>();

			for(int j=0;j<nVert;j++) {
				ArrayList<Integer> tmp=new ArrayList<Integer>();
				vertices.add(tmp);
			}

			
			for(int j=1;j<fileList.size();j++) {
				int tmp[]= new int[3];
				tmp[0]=Integer.parseInt(fileList.get(j).split(" ")[0]);
				tmp[1]=Integer.parseInt(fileList.get(j).split(" ")[1]);
				tmp[2]=Integer.parseInt(fileList.get(j).split(" ")[2]);
				edges.add(tmp);
					
			}
			
			for(int j=0;j<edges.size();j++) {
				vertices.get(edges.get(j)[0]-1).add(j);
				vertices.get(edges.get(j)[1]-1).add(j);
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}		
}
