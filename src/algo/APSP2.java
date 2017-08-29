package algo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class APSP2 {
	FileReader fr;
	BufferedReader br;
	int nVert,nEdge;
	ArrayList<ArrayList<Integer>> vertices;
	ArrayList<int[]> edges;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		APSP2 obj=new APSP2();
		obj.readFile("g1");
		int A[][]=obj.bellmanFord(1);
		for(int i=0;i<obj.nVert;i++) {
			
		}
		
//		for(int i=0;i<obj.vertices.size()-900;i++) {
//			System.out.print(i+": ");
//			for(int j:obj.vertices.get(i)) {
//				System.out.print(j+" ");
//			}
//			System.out.println();
//		}
		
	}
	public int[][] bellmanFord(int s) {
		int A[][]=new int[nVert-1][nVert];
		for(int i=0;i<nVert;i++) {
			if(s-1==i) {
				A[0][s-1]=0;
			}
			else
				A[0][i]=99999;
		}
		
		for(int i=1;i<=nVert-1;i++) {
			for(int j=0;j<nEdge;j++) {
//				if(A[i-1][edges.get(j)[0]]!=99999) {
					if(A[i-1][edges.get(j)[1]]>A[i-1][edges.get(j)[0]+edges.get(j)[2]]) {
						A[i][edges.get(j)[1]]=A[i-1][edges.get(j)[0]+edges.get(j)[2]];
					}					
//				}
			}
			
		}
		return A;
	}
	public void readFile(String filename) {
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
