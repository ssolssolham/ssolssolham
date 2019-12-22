import sys

N = int(sys.stdin.readline().rstrip())
result = 0

# 작은부분(3)으로 먼저 채우고 
# 큰부분(5)로 채우기
while(1):
    if N % 5 == 0:
        result += (N // 5)
        print(result)
        break
    
    N -= 3
    result += 1
    
    if N < 0:
        print(-1)
        break