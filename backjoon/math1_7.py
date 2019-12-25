import sys
T = int(sys.stdin.readline().rstrip())

arr = [[0] * 14 for i in range(15)]


for i in range(1,15):
    arr[0][i-1] = i

for i in range(1,15):
    sumr = 0
    for j in range(0,14):
        sumr += arr[i-1][j]
        arr[i][j] = sumr

for i in range(0,T):
    k = int(sys.stdin.readline().rstrip())
    n = int(sys.stdin.readline().rstrip())
    print(arr[k][n-1])