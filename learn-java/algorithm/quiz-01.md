Write a function:

class Solution { public int solution(int[] A); }

content_copy

that, given an array A of N integers, 
returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].

---
32 = 16 bit
100 000 / 32 = 3 125
3 125 / 8 = 391 byte

4 096 = 12 bit
8 192 / 8 = 1 024 byte

8 192 * 32 = 262 144

int - 32 bits = 4 bytes
100 000 * 4 = 400 000 bytes
---
short [32]
byte [1024]