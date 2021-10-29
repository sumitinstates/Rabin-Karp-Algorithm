package com.sumit;

public class RKP {
	
	public int patternSearch(String text, String pattern) 
	{
		int q =26;
		int n = text.length();
		int m = pattern.length();
		int j;
		
		long patternHash = createHash(pattern, m-1);
		System.out.println("Pattern hash is : " + patternHash);
		
		long textHash = createHash(text, m-1);
		System.out.println("Text hash is : " + textHash);
		
		for(int i=0; i<=n-m; i++) 
		{
			if(patternHash==textHash) 
			{
				System.out.println("matches");
				
				for(j=0; j<m; j++) 
				{
					//checking character by character
					if(text.charAt(i+j)!=pattern.charAt(j)) 
					{
						break;
					}
				}
				
				//if j is end of pattern 
				if(j==m) 
				{
					return i;
				}
				
			}
			
			if(i<n-m) 
			{
				textHash = recalculateHash(text, textHash, i, i+m, q, m);
				System.out.println("Recalculated hash : " + textHash);

			}
		}
		
		return -1;
	}
	
	private long recalculateHash(String text, long oldHash, int oldIndex, int newIndex, int q, int m) {
		
		long newHash = oldHash - text.charAt(oldIndex);
		newHash = newHash/q;
		newHash += text.charAt(newIndex) * Math.pow(q, m-1);
		return newHash;
	}
	
	public long createHash(String s, int length) 
	{
		int q = 26;
		long hash= 0;
		
		for(int i=0; i<=length; i++) 
		{
			hash += s.charAt(i) * Math.pow(q, i);
		}
		return hash;
	}
	
	public static void main(String[] args) 
	{
		RKP r = new RKP();
		System.out.println(r.patternSearch("Sumit", "mit"));
		System.out.println(r.patternSearch("Sumit", "smit"));
		System.out.println(r.patternSearch("Sumit", "Smit"));
		System.out.println(r.patternSearch("Sumit", "umi"));
		System.out.println(r.patternSearch("Sumit", "sum"));
	}

}
