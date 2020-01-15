import java.util.Arrays;

public class Skew{
	
	
	static boolean leq(Integer a1, Integer a2,   Integer b1, Integer b2) {
		return(a1 < b1 || (a1 == b1 && a2 <= b2));
	}
	
	static boolean leq(Integer a1, Integer a2, Integer a3,   Integer b1, Integer b2, Integer b3) {
		return(a1 < b1 || (a1 == b1 && leq(a2,a3, b2,b3)));
	}
	
	static void radixPass(Integer[] a, Integer[] b, Integer[] r, Integer n, Integer K) {
		Integer[] c = new Integer[K + 1];
		for(Integer i=0; i<=K;i++) c[i] = 0;
		for(Integer i=0; i<n;i++) c[r[a[i]]]++;
		for(Integer i=0,sum=0; i<=K;i++) {
			Integer t = c[i];  c[i] = sum;  sum += t;
		}
		for(Integer i=0; i<n; i++) b[c[r[a[i]]]++] = a[i];
	}
	
	private final static void radixPass(int [] src, int [] dst, int [] v, int vi,
	        final int n, final int K, int start, int [] cnt)
	    {
	        // check counter array's size.
	        assert cnt.length >= K + 1;
	        Arrays.fill(cnt, 0, K + 1, 0);

	        // count occurrences
	        for (int i = 0; i < n; i++)
	            cnt[v[start + vi + src[i]]]++;

	        // exclusive prefix sums
	        for (int i = 0, sum = 0; i <= K; i++)
	        {
	            final int t = cnt[i];
	            cnt[i] = sum;
	            sum += t;
	        }

	        // sort
	        for (int i = 0; i < n; i++)
	            dst[cnt[v[start + vi + src[i]]]++] = src[i];
	    }
	
	static void suffixArray(Integer[] s, Integer[] SA, Integer n, Integer K) {
		Integer n0=(n+2)/3, n1=(n+1)/3, n2=n/3, n02=n0+n2;
		Integer[] s12  = new Integer[n02 + 3];  s12[n02]= s12[n02+1]= s12[n02+2]=0;
		Integer[] SA12 = new Integer[n02 + 3]; SA12[n02]=SA12[n02+1]=SA12[n02+2]=0;
		Integer[] s0   = new Integer[n0];
		Integer[] SA0  = new Integer[n0];
		
		for (Integer i=0, j=0;  i < n+(n0-n1);  i++) if (i%3 != 0) s12[j++] = i;
		
		// radixPass(s12 , SA12, s+2, n02, K);
		radixPass(s12 , SA12, Arrays.copyOfRange(s, 2, s.length + 3), n02, K);
		// radixPass(SA12, s12 , s+1, n02, K);
		radixPass(SA12, s12 , Arrays.copyOfRange(s, 1, s.length + 2), n02, K);
		radixPass(s12 , SA12, s  , n02, K);
		
		Integer name = 0, c0 = -1, c1 = -1, c2 = -1;
		for(Integer i=0; i<n02;  i++) {
			if (s[SA12[i]] != c0 || s[SA12[i]+1] != c1 || s[SA12[i]+2] != c2) {
				name++; c0 = s[SA12[i]]; c1 = s[SA12[i]+1]; c2 = s[SA12[i]+2];
			}
			if (SA12[i]%3==1) {
				s12[SA12[i]/3] = name;
			}
			else {
				s12[SA12[i]/3 + n0] = name;
			}
		}
		if (name < n02) {
			suffixArray(s12, SA12, n02, name);
			for(Integer i=0; i<n02;  i++) s12[SA12[i]]=i+1;
		}
		else {
			for(Integer i=0; i<n02;  i++) SA12[s12[i] - 1] = i;
		}
		for (Integer i=0, j=0; i < n02; i++) if (SA12[i] < n0) s0[j++] = 3*SA12[i];
		radixPass(s0, SA0, s, n0, K);
		System.out.println(n0);
		for (Integer p=0,  t=n0-n1,  k=0;  k < n;  k++) {
			// #define GetI() (SA12[t] < n0 ? SA12[t]*3+1:(SA12[t] - n0)*3+2)
			Integer i = SA12[t] < n0 ? SA12[t]*3+1:(SA12[t] - n0)*3+2;
			Integer j = SA0[p];
			if (SA12[t] < n0 ? leq(s[i], s12[SA12[t] + n0], s[j], s12[j/3]) :
				leq(s[i],s[i+1],s12[SA12[t]-n0+1], s[j],s[j+1],s12[j/3+n0])) {
				SA[k] = i;  t++;
				if (t == n02) {
					for (k++;  p < n0;  p++, k++) SA[k] = SA0[p];
				}
				else {
					SA[k] = j;  p++;
					if (p == n0) {
						for (k++;  t < n02;  t++, k++) SA[k] = SA12[t] < n0 ? SA12[t]*3+1:(SA12[t] - n0)*3+2;
					}
				}
			}
		}
	}
	
	
	static final int[] suffixArray(int [] s, int [] SA, int n, final int K, int start, int [] cnt)
    {
        final int n0 = (n + 2) / 3, n1 = (n + 1) / 3, n2 = n / 3, n02 = n0 + n2;

        final int [] s12 = new int [n02 + 3];
        s12[n02] = s12[n02 + 1] = s12[n02 + 2] = 0;
        final int [] SA12 = new int [n02 + 3];
        SA12[n02] = SA12[n02 + 1] = SA12[n02 + 2] = 0;
        final int [] s0 = new int [n0];
        final int [] SA0 = new int [n0];

        /*
         * generate positions of mod 1 and mod 2 suffixes the "+(n0-n1)" adds a dummy mod
         * 1 suffix if n%3 == 1
         */
        for (int i = 0, j = 0; i < n + (n0 - n1); i++)
            if ((i % 3) != 0) s12[j++] = i;

        // lsb radix sort the mod 1 and mod 2 triples
        radixPass(s12, SA12, s, +2, n02, K, start, cnt);
        radixPass(SA12, s12, s, +1, n02, K, start, cnt);
        radixPass(s12, SA12, s, +0, n02, K, start, cnt);

        // find lexicographic names of triples
        int name = 0, c0 = -1, c1 = -1, c2 = -1;
        for (int i = 0; i < n02; i++)
        {
            if (s[start + SA12[i]] != c0 || s[start + SA12[i] + 1] != c1
                || s[start + SA12[i] + 2] != c2)
            {
                name++;
                c0 = s[start + SA12[i]];
                c1 = s[start + SA12[i] + 1];
                c2 = s[start + SA12[i] + 2];
            }

            if ((SA12[i] % 3) == 1)
            {
                // left half
                s12[SA12[i] / 3] = name;
            }
            else
            {
                // right half
                s12[SA12[i] / 3 + n0] = name;
            }
        }

        // recurse if names are not yet unique
        if (name < n02)
        {
            cnt = suffixArray(s12, SA12, n02, name, start, cnt);
            // store unique names in s12 using the suffix array
            for (int i = 0; i < n02; i++)
                s12[SA12[i]] = i + 1;
        }
        else
        {
            // generate the suffix array of s12 directly
            for (int i = 0; i < n02; i++)
                SA12[s12[i] - 1] = i;
        }

        // stably sort the mod 0 suffixes from SA12 by their first character
        for (int i = 0, j = 0; i < n02; i++)
            if (SA12[i] < n0) s0[j++] = 3 * SA12[i];
        radixPass(s0, SA0, s, 0, n0, K, start, cnt);

        // merge sorted SA0 suffixes and sorted SA12 suffixes
        for (int p = 0, t = n0 - n1, k = 0; k < n; k++)
        {
            // pos of current offset 12 suffix
            final int i = (SA12[t] < n0 ? SA12[t] * 3 + 1 : (SA12[t] - n0) * 3 + 2);
            // pos of current offset 0 suffix
            final int j = SA0[p];

            if (SA12[t] < n0 ? leq(s[start + i], s12[SA12[t] + n0], s[start + j],
                s12[j / 3]) : leq(s[start + i], s[start + i + 1], s12[SA12[t] - n0 + 1],
                s[start + j], s[start + j + 1], s12[j / 3 + n0]))
            {
                // suffix from SA12 is smaller
                SA[k] = i;
                t++;
                if (t == n02)
                {
                    // done --- only SA0 suffixes left
                    for (k++; p < n0; p++, k++)
                        SA[k] = SA0[p];
                }
            }
            else
            {
                SA[k] = j;
                p++;
                if (p == n0)
                {
                    // done --- only SA12 suffixes left
                    for (k++; t < n02; t++, k++)
                    {
                        SA[k] = (SA12[t] < n0 ? SA12[t] * 3 + 1 : (SA12[t] - n0) * 3 + 2);
                    }
                }
            }
        }

        return cnt;
    }
	
	static final int max(int [] input, int start, int length)
    {
        assert length >= 1;

        int max = input[start];
        for (int i = length - 2, index = start + 1; i >= 0; i--, index++)
        {
            final int v = input[index];
            if (v > max)
            {
                max = v;
            }
        }

        return max;
    }
	
	
	public static Integer [] buildSuffixArray(int [] input, int start, int length)
    {
        final int alphabetSize = max(input, start, length);
        final int [] SA = new int [length + 3];

        // Preserve the tail of the input (destroyed when constructing the array).
        final int [] tail = new int [3];
        System.arraycopy(input, start + length, tail, 0, 3);
        Arrays.fill(input, start + length, start + length + 3, 0);

        suffixArray(input, SA, length, 256, start, new int [256 + 2]);

        // Reconstruct the input's tail.
        System.arraycopy(tail, 0, input, start + length, 3);
//        for(int num :  SA) {
//        	System.out.println(((Integer)num).toString());
//        }
        //Arrays.copyOfRange(SA, 0, SA.length - 3);
        Integer[] result = Arrays.stream(SA).boxed().toArray(Integer[]::new);
        return result;
    }
}
