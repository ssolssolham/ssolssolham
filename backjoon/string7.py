import sys
N = sys.stdin.readline().rstrip().split()
A = N[0] 
B = N[1]
list_A = list(A)
list_B = list(B)
result = ''


for i in range(2, -1, -1):
    if int(list_A[i]) > int(list_B[i]):
        result = N[0]
        break
    elif int(list_A[i]) < int(list_B[i]):
        result = N[1]
        break

arr = list(result)

for i in range(0, len(A) // 2 ):
    tmp = ''    
    tmp = arr[i]
    arr[i] = arr[len(A) - i - 1]
    arr[len(A) - i - 1] = tmp
    
print(arr[0] + arr[1] + arr[2])

