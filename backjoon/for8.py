import sys
T = int(sys.stdin.readline())
for i in range(1, T+1):
    A, B = map(int,sys.stdin.readline().rstrip().split())
    print('Case #' + str(i) + ': ' + str(A) + ' + ' + str(B) + ' = ' + str(A+B))