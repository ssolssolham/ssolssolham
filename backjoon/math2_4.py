import sys
# 에라토스테네스 체 4

ipt = -1
ipts = []
max = 0
while ipt != 0:
    ipt = int(sys.stdin.readline().rstrip())
    ipts.append(ipt)
    if max < ipt:
        max = ipt
double_max = 2 * max

a = [False,False] + [True] * (double_max - 1)
primes = []

for i in range(2, double_max + 1):
    if a[i]:
        primes.append(i)
        for j in range(2*i, double_max+1, i):
            a[j] = False

ipts.pop()
for i in ipts:
    sum = 0
    for j in range(i + 1, 2*i + 1):
        if a[j]:
            sum += 1
    print(sum)
            
