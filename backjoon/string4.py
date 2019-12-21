import sys

N = int(sys.stdin.readline().rstrip())

for i in range(0,N):
    S = sys.stdin.readline().rstrip().split()
    A = int(S[0])
    B = S[1]
    
    result = ''
    
    for item in list(B):
        result += (item * A)
    print(result)