import sys
# 에라토스테네스체 3
inp = sys.stdin.readline().rstrip().split()
M = int(inp[0])
N = int(inp[1])

a = [False,False] + [True] * (N-1)
primes = []

for i in range(2, N+1):
    if a[i]:
        primes.append(i)
        for j in range(2*i, N+1, i):
            a[j] = False

for i in primes:
    if i >= M and i <= N:
        print(i)