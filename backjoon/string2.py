import sys
N = int(sys.stdin.readline().rstrip())
arr  = sys.stdin.readline().rstrip()
sum = 0

for item in range(0,N):
    sum += int(arr[item])
    
print(sum)