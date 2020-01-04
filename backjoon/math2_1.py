import sys
# 에라토스테네스의 체 
N = int(sys.stdin.readline().rstrip())
Nums = sys.stdin.readline().rstrip().split()

n = 1000
a = [False,False] + [True]*(n-1)
primes = set()

for i in range(2,n+1):
    if a[i]:
        primes.add(i)
        for j in range(2*i, n+1, i):
            a[j] = False

result = 0            
for i in range(0,N):
    if int(Nums[i]) in primes:
        result += 1
print(result)