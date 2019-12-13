import sys
N = int(sys.stdin.readline().rstrip())
for item in range(0,N):
    A = sys.stdin.readline().rstrip()
    cnt = 0
    sumr = 0
    for item in list(A):
        if item == 'O':
            cnt += 1
            sumr += cnt
        else : 
            cnt = 0
    print(sumr)
