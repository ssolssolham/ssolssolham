import sys
T = int(sys.stdin.readline().rstrip())

for i in range(0, T):
    H, W, N = map(int,sys.stdin.readline().rstrip().split())
    A = (N - 1) // H + 1
    B = N % H
    
    if B == 0:
        B = H
        
    result = str(B)
    tail = ''
    
    if A < 10:
        tail += ('0' + str(A))
    else:
        tail += str(A)
    print(result + tail)

    
    
    # 10 // 6 = 1, 1 + 1 뒷자리
    # 10 % 6 = 4, 4 앞자리
      