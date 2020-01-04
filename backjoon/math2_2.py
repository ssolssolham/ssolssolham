import sys
# 에라토스테네스의 체2

M = int(sys.stdin.readline().rstrip())
N = int(sys.stdin.readline().rstrip())

a = [False,False] + [True]*(N-1)
primes = set()

for i in range(2, N+1):
    if a[i]:
        primes.add(i)
        for j in range(2*i, N+1, i):
            a[j] = False

sum = 0
first = True
first_num = 0
for i in range(M, N+1):
    if first and i in primes:
        first_num = i
        first = False
    if i in primes:
        sum += i

if first:
    print(-1)
else:
    print(sum)
    print(first_num)
        
    
