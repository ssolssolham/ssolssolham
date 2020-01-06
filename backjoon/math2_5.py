import sys
T = int(sys.stdin.readline().rstrip())
ipt = []
max = 0

for i in range(0,T):
    num = int(sys.stdin.readline().rstrip())
    ipt.append(num)
    if num > max:
        max = num
    
a = [False,False] + [True] *  (max -1)
primes = []

for i in range(2, max + 1):
    if a[i]:
        primes.append(i)
        for j in range(2*i, max + 1, i):   
            a[j] = False

for item in ipt:
    prime1 = int(item / 2)
    prime2 = int(item / 2)
    while True:
        if a[prime1] and a[prime2]:
            print(str(prime1) + " " + str(prime2))
            break
        prime1 -= 1
        prime2 += 1
        
    

